package com.momo.domain.schedule.service;

import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.momo.common.ServiceTest;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.entity.Groups;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.schedule.entity.Schedule;
import com.momo.domain.schedule.repository.AttendanceRepository;
import com.momo.domain.schedule.repository.ScheduleRepository;
import com.momo.domain.schedule.dto.AttendanceCreateRequest;
import com.momo.domain.schedule.dto.AttendanceCreateRequests;
import com.momo.domain.user.entity.User;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@DisplayName("출석 서비스 테스트")
public class AttendanceServiceTest extends ServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private AttendanceRepository attendanceRepository;

    @InjectMocks
    private AttendanceService attendanceService;

    private User manager;

    private User user;

    private Groups group;

    private Schedule schedule;

    @BeforeEach
    void setUp() {
        manager = User.builder().id(1L).build();
        user = User.builder().id(2L).build();
        group = Groups.builder()
            .id(1L)
            .manager(manager)
            .build();
        schedule = Schedule.builder()
            .id(1L)
            .group(group)
            .author(manager)
            .build();
    }

    @Test
    void 일정_출석_체크_테스트() {
        AttendanceCreateRequests attendanceCreateRequests = AttendanceCreateRequests.builder()
            .groupId(group.getId())
            .scheduleId(schedule.getId())
            .attendanceCreateRequests(List.of(
                AttendanceCreateRequest.builder()
                    .userId(1L)
                    .isAttend(true)
                    .build(),
                AttendanceCreateRequest.builder()
                    .userId(2L)
                    .isAttend(true)
                    .build()
            ))
            .build();

        given(groupRepository.findById(any())).willReturn(of(group));
        given(scheduleRepository.findById(any())).willReturn(of(schedule));

        attendanceService.create(manager, attendanceCreateRequests);

        verify(groupRepository).findById(any());
        verify(scheduleRepository).findById(any());
        assertThat(schedule.isAttendanceCheck()).isTrue();
    }

    @Test
    void 모임_관리자가_아니면_일정_출석_체크_테스트를_실패한다() {
        given(groupRepository.findById(any())).willReturn(of(group));
        assertThatThrownBy(() -> attendanceService.create(user, AttendanceCreateRequests.builder().build()))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_MANAGER_AUTHORIZED.getMessage());
    }
}
