package com.momo.domain.group.service;

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
import com.momo.domain.group.entity.Participant;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.group.repository.ParticipantRepository;
import com.momo.domain.group.dto.ParticipantResponse;
import com.momo.domain.user.entity.User;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@DisplayName("모임 참여자 서비스 테스트")
public class ParticipantServiceTest extends ServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private ParticipantService participantService;

    private User manager;

    private User user1;

    private User user2;

    @BeforeEach
    void setUp() {
        manager = User.builder().id(1L).build();
        user1 = User.builder().id(2L).build();
        user2 = User.builder().id(3L).build();
    }

    @Test
    void 모임_참여자_목록_조회_테스트() {
        Group group = Group.builder()
            .manager(manager)
            .build();
        Participant participant1 = Participant.builder()
            .id(1L)
            .group(group)
            .user(user1)
            .scheduleCount(50)
            .attendanceCount(30)
            .build();
        Participant participant2 = Participant.builder()
            .id(2L)
            .group(group)
            .user(user2)
            .scheduleCount(50)
            .attendanceCount(15)
            .build();
        List<Participant> participants = List.of(participant1, participant2);

        given(groupRepository.findById(any())).willReturn(of(group));
        given(participantRepository.findAllByGroup(any())).willReturn(participants);

        List<ParticipantResponse> actual = participantService.findByGroupId(manager, 1L);

        verify(groupRepository).findById(any());
        verify(participantRepository).findAllByGroup(any());
        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.size()).isEqualTo(2),
            () -> assertThat(actual.get(0).getUserId()).isEqualTo(user1.getId()),
            () -> assertThat(actual.get(0).getNickname()).isEqualTo(user1.getNickname()),
            () -> assertThat(actual.get(0).getImageUrl()).isEqualTo(user1.getImageUrl()),
            () -> assertThat(actual.get(0).getAttendanceRate()).isNotNull(),
            () -> assertThat(actual.get(1).getUserId()).isEqualTo(user2.getId()),
            () -> assertThat(actual.get(1).getNickname()).isEqualTo(user2.getNickname()),
            () -> assertThat(actual.get(1).getImageUrl()).isEqualTo(user2.getImageUrl()),
            () -> assertThat(actual.get(1).getAttendanceRate()).isNotNull()
        );
    }

    @Test
    void 모임_관리자가_아니면_참여자_목록_조회_테스트가_실패한다() {
        Group group = Group.builder()
            .manager(manager)
            .build();

        given(groupRepository.findById(any())).willReturn(of(group));

        assertThatThrownBy(() -> participantService.findByGroupId(user1, 1L))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_MANAGER_AUTHORIZED.getMessage());
    }

    @Test
    void 모임_탈퇴_테스트() {
        Group group = Group.builder()
            .manager(manager)
            .build();
        given(groupRepository.findById(any())).willReturn(of(group));
        participantService.withdrawByGroupId(user1, 1L);
        verify(groupRepository).findById(any());
    }

    @Test
    void 모임_관리자가_탈퇴를_시도하면_테스트가_실패한다() {
        Group group = Group.builder()
            .manager(manager)
            .build();

        given(groupRepository.findById(any())).willReturn(of(group));

        assertThatThrownBy(() -> participantService.withdrawByGroupId(manager, 1L))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_MANAGER_WITHDRAW_NOT_ALLOW.getMessage());
    }
}
