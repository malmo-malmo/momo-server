package com.momo.domain.schedule.service;

import static com.momo.GroupFixture.getGroupWithId;
import static com.momo.ScheduleFixture.getGroupSchedulesRequest;
import static com.momo.ScheduleFixture.getScheduleCreateRequest;
import static com.momo.ScheduleFixture.getScheduleWithId;
import static com.momo.UserFixture.getUserWithId;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.momo.common.ServiceTest;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.group.repository.ParticipantRepository;
import com.momo.domain.schedule.dto.GroupScheduleResponse;
import com.momo.domain.schedule.dto.GroupScheduleResponses;
import com.momo.domain.schedule.dto.GroupSchedulesRequest;
import com.momo.domain.schedule.dto.ScheduleCreateRequest;
import com.momo.domain.schedule.entity.Schedule;
import com.momo.domain.schedule.repository.ScheduleRepository;
import com.momo.domain.schedule.service.impl.ScheduleServiceImpl;
import com.momo.domain.user.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

@DisplayName("일정 서비스 테스트")
public class ScheduleServiceTest extends ServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private ParticipantRepository participantRepository;

    private ScheduleService scheduleService;

    private User manager;
    private User user;
    private Group group;

    @BeforeEach
    void setUp() {
        manager = getUserWithId();
        user = getUserWithId();
        group = getGroupWithId(manager);
        scheduleService = new ScheduleServiceImpl(scheduleRepository, groupRepository, participantRepository);
    }

    @Test
    void 일정_생성_테스트() {
        ScheduleCreateRequest request = getScheduleCreateRequest(group.getId(), true, LocalDateTime.now());
        Schedule schedule = getScheduleWithId(manager, group);

        given(groupRepository.findById(any())).willReturn(of(group));
        given(scheduleRepository.save(any())).willReturn(schedule);
        given(scheduleRepository.findGroupResponseById(any(), any()))
            .willReturn(GroupScheduleResponse.builder().id(schedule.getId()).build());

        GroupScheduleResponse actual = scheduleService.create(manager, request);

        verify(groupRepository).findById(any());
        verify(scheduleRepository).save(any());
        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.getId()).isEqualTo(schedule.getId())
        );
    }

    @Test
    void 모임_관리자가_아니면_일정_생성_테스트를_실패한다() {
        ScheduleCreateRequest request = getScheduleCreateRequest(group.getId(), true, LocalDateTime.now());

        given(groupRepository.findById(any())).willReturn(of(group));

        assertThatThrownBy(() -> scheduleService.create(user, request))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_MANAGER_AUTHORIZED.getMessage());
    }

    @Test
    void 모임_일정_목록_조회_테스트() {
        GroupSchedulesRequest request = getGroupSchedulesRequest(group.getId());

        List<GroupScheduleResponse> response = List.of(
            GroupScheduleResponse.builder().id(1L).build(),
            GroupScheduleResponse.builder().id(2L).build()
        );

        given(groupRepository.findById(any())).willReturn(of(group));
        given(participantRepository.existsByGroupAndUser(any(), any())).willReturn(true);
        given(scheduleRepository.findAllByGroupAndUserOrderByCreatedDateDesc(any(), any(), any())).willReturn(response);

        GroupScheduleResponses actual = scheduleService.findPageByUserAndGroupId(manager, request);

        verify(groupRepository).findById(any());
        verify(participantRepository).existsByGroupAndUser(any(), any());
        verify(scheduleRepository).findAllByGroupAndUserOrderByCreatedDateDesc(any(), any(), any());
        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.getManagerId()).isEqualTo(manager.getId()),
            () -> assertThat(actual.getGroupScheduleResponses().size()).isEqualTo(response.size())
        );
    }

    @Test
    void 모임_참여자가_아니면_일정_목록_조회_테스트를_실패한다() {
        GroupSchedulesRequest request = getGroupSchedulesRequest(group.getId());

        given(groupRepository.findById(any())).willReturn(of(group));
        given(participantRepository.existsByGroupAndUser(any(), any())).willReturn(false);

        assertThatThrownBy(() -> scheduleService.findPageByUserAndGroupId(manager, request))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED.getMessage());
    }
}
