package com.momo.domain.schedule.service;

import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.momo.common.ServiceTest;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.schedule.dto.AttendanceCreateRequest;
import com.momo.domain.schedule.dto.AttendanceCreateRequests;
import com.momo.domain.schedule.dto.AttendanceResponse;
import com.momo.domain.schedule.dto.AttendanceUpdateRequest;
import com.momo.domain.schedule.dto.AttendanceUpdateRequests;
import com.momo.domain.schedule.entity.Attendance;
import com.momo.domain.schedule.entity.Schedule;
import com.momo.domain.schedule.repository.AttendanceRepository;
import com.momo.domain.schedule.repository.ScheduleRepository;
import com.momo.domain.schedule.service.impl.AttendanceServiceImpl;
import com.momo.domain.user.entity.User;
import com.momo.domain.user.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

@DisplayName("출석 서비스 테스트")
public class AttendanceServiceTest extends ServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private UserRepository userRepository;

    private AttendanceService attendanceService;

    private User manager;

    private User user;

    private Group group;

    private Schedule schedule;

    @BeforeEach
    void setUp() {
        manager = User.builder().id(1L).build();
        user = User.builder().id(2L).build();
        group = Group.builder()
            .id(1L)
            .manager(manager)
            .build();
        schedule = Schedule.builder()
            .id(1L)
            .group(group)
            .author(manager)
            .build();
        attendanceService = new AttendanceServiceImpl(groupRepository, scheduleRepository, attendanceRepository,
            userRepository);
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
        given(userRepository.findById(any())).willReturn(Optional.of(User.builder().build()));

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

    @Test
    void 모임_관리자가_출석_수정_테스트를_성공한다() {
        given(groupRepository.findById(anyLong())).willReturn(Optional.of(group));
        given(scheduleRepository.findById(anyLong())).willReturn(Optional.of(schedule));
        Attendance attendance = Attendance.builder().id(1L).group(group).schedule(schedule).isAttend(false).build();
        given(attendanceRepository.findById(any())).willReturn(Optional.of(attendance));
        AttendanceUpdateRequests requests = AttendanceUpdateRequests.builder()
            .groupId(group.getId())
            .scheduleId(schedule.getId())
            .attendanceUpdateRequests(List.of(
                AttendanceUpdateRequest.builder()
                    .attendanceId(attendance.getId())
                    .isAttend(true)
                    .build()
            )).build();
        attendanceService.updates(manager, requests);
        verify(attendanceRepository).findById(attendance.getId());
        assertThat(attendance.isAttend()).isTrue();
    }

    @Test
    void 모임_관리자가_아니면_출석_수정_테스트를_실패한다() {
        given(groupRepository.findById(anyLong())).willReturn(Optional.of(group));
        given(scheduleRepository.findById(anyLong())).willReturn(Optional.of(schedule));
        given(attendanceRepository.findById(any())).willReturn(Optional.of(Attendance.builder().group(group).build()));
        AttendanceUpdateRequests requests = AttendanceUpdateRequests.builder()
            .groupId(group.getId())
            .scheduleId(schedule.getId())
            .attendanceUpdateRequests(List.of(
                AttendanceUpdateRequest.builder()
                    .isAttend(true)
                    .build()
            )).build();

        assertThatThrownBy(() -> attendanceService.updates(user, requests))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_MANAGER_AUTHORIZED.getMessage());
    }

    @Test
    void 모임_관리자가_출석_모임_목록을_조회_테스트를_성공한다() {
        Attendance attendance = Attendance.builder()
            .id(1L)
            .user(User.builder().nickname("테스트 유저").build())
            .isAttend(false)
            .build();
        given(groupRepository.findById(anyLong())).willReturn(Optional.of(group));
        given(attendanceRepository.findByGroup(any(Group.class))).willReturn(List.of(attendance));
        List<AttendanceResponse> responses = attendanceService.findGroupAttendances(manager, group.getId());
        assertThat(responses.size()).isEqualTo(1);

        AttendanceResponse response = responses.get(0);
        Assertions.assertAll(
            () -> assertThat(response.getAttendanceId()).isEqualTo(attendance.getId()),
            () -> assertThat(response.getUsername()).isEqualTo(attendance.getUser().getNickname()),
            () -> assertThat(response.getIsAttend()).isFalse(),
            () -> assertThat(response.getAttainmentRate()).isEqualTo(100)
        );
    }

    @Test
    void 모임_관리자가_아니면_출석_모임_목록_조회_테스트를_실패한다() {
        given(groupRepository.findById(anyLong())).willReturn(Optional.of(group));

        assertThatThrownBy(() -> attendanceService.findGroupAttendances(user, group.getId()))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_MANAGER_AUTHORIZED.getMessage());
    }
}
