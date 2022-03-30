package com.momo.domain.schedule.service;

import static com.momo.AttendanceFixture.getAttendanceCreateRequest;
import static com.momo.AttendanceFixture.getAttendanceCreateRequests;
import static com.momo.AttendanceFixture.getAttendanceUpdateRequest;
import static com.momo.AttendanceFixture.getAttendanceUpdateRequests;
import static com.momo.AttendanceFixture.getAttendanceWithId;
import static com.momo.GroupFixture.getGroupWithId;
import static com.momo.ParticipantFixture.getParticipantWithId;
import static com.momo.ScheduleFixture.getScheduleWithId;
import static com.momo.UserFixture.getUserWithId;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.momo.common.ServiceTest;
import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.entity.Group;
import com.momo.group.entity.Participant;
import com.momo.group.repository.GroupRepository;
import com.momo.group.repository.ParticipantRepository;
import com.momo.schedule.dto.AttendanceCreateRequests;
import com.momo.schedule.dto.AttendanceResponse;
import com.momo.schedule.dto.AttendanceUpdateRequests;
import com.momo.schedule.entity.Attendance;
import com.momo.schedule.entity.Schedule;
import com.momo.schedule.repository.AttendanceRepository;
import com.momo.schedule.repository.ScheduleRepository;
import com.momo.schedule.service.AttendanceService;
import com.momo.schedule.service.impl.AttendanceServiceImpl;
import com.momo.user.domain.User;
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
    private ParticipantRepository participantRepository;

    private AttendanceService attendanceService;

    private User manager;
    private User user;
    private Group group;
    private Participant participant;
    private Schedule schedule;

    @BeforeEach
    void setUp() {
        manager = getUserWithId();
        user = getUserWithId();
        group = getGroupWithId(manager);
        participant = getParticipantWithId(group, user);
        schedule = getScheduleWithId(manager, group);
        attendanceService = new AttendanceServiceImpl(scheduleRepository, attendanceRepository, participantRepository);
    }

    @Test
    void 일정_출석_체크_테스트() {
        AttendanceCreateRequests requests = getAttendanceCreateRequests(
            schedule.getId(),
            List.of(getAttendanceCreateRequest(participant.getId(), true))
        );

        given(scheduleRepository.findScheduleWithGroupById(anyLong())).willReturn(of(schedule));
        given(participantRepository.findAllByIdsAndUser(any(), any())).willReturn(List.of(participant));

        attendanceService.createScheduleAttendances(manager, requests);

        verify(scheduleRepository).findScheduleWithGroupById(any());
        verify(participantRepository).findAllByIdsAndUser(any(), any());
        verify(attendanceRepository).saveAll(any());
        assertThat(schedule.isAttendanceCheck()).isTrue();
    }

    @Test
    void 모임_관리자가_아니면_일정_출석_체크_테스트를_실패한다() {
        AttendanceCreateRequests requests = getAttendanceCreateRequests(
            schedule.getId(),
            List.of(getAttendanceCreateRequest(participant.getId(), true))
        );

        given(scheduleRepository.findScheduleWithGroupById(anyLong())).willReturn(Optional.of(schedule));

        assertThatThrownBy(() -> attendanceService.createScheduleAttendances(user, requests))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_MANAGER_AUTHORIZED.getMessage());
    }

    @Test
    void 모임_관리자가_출석_수정_테스트를_성공한다() {
        Attendance attendance = getAttendanceWithId(schedule, participant, false);
        AttendanceUpdateRequests requests = getAttendanceUpdateRequests(
            schedule.getId(),
            List.of(getAttendanceUpdateRequest(attendance.getId(), true))
        );

        given(scheduleRepository.findScheduleWithGroupById(anyLong())).willReturn(Optional.of(schedule));
        given(attendanceRepository.findAllByIds(anyList())).willReturn(List.of(attendance));

        attendanceService.updateScheduleAttendances(manager, requests);

        verify(attendanceRepository).findAllByIds(any());
        Assertions.assertAll(
            () -> assertThat(attendance.isAttend()).isTrue()
        );
    }

    @Test
    void 모임_관리자가_아니면_출석_수정_테스트를_실패한다() {
        Attendance attendance = getAttendanceWithId(schedule, participant, false);
        AttendanceUpdateRequests requests = getAttendanceUpdateRequests(
            schedule.getId(),
            List.of(getAttendanceUpdateRequest(attendance.getId(), true))
        );

        given(scheduleRepository.findScheduleWithGroupById(anyLong())).willReturn(Optional.of(schedule));

        assertThatThrownBy(() -> attendanceService.updateScheduleAttendances(user, requests))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_MANAGER_AUTHORIZED.getMessage());
    }

    @Test
    void 모임_관리자가_출석_모임_목록을_조회_테스트를_성공한다() {
        Attendance attendance = getAttendanceWithId(schedule, participant, false);

        given(scheduleRepository.findScheduleWithGroupById(anyLong())).willReturn(Optional.of(schedule));
        given(attendanceRepository.findBySchedule(any())).willReturn(List.of(attendance));

        List<AttendanceResponse> responses = attendanceService.findScheduleAttendances(manager, schedule.getId());

        Assertions.assertAll(
            () -> assertThat(responses.size()).isEqualTo(1),
            () -> assertThat(responses.get(0).getAttendanceId()).isEqualTo(attendance.getId()),
            () -> assertThat(responses.get(0).getIsAttend()).isFalse(),
            () -> assertThat(responses.get(0).getAttendanceRate()).isEqualTo(100),
            () -> assertThat(responses.get(0).getNickname())
                .isEqualTo(attendance.getParticipant().getUser().getNickname())
        );
    }

    @Test
    void 모임_관리자가_아니면_출석_모임_목록_조회_테스트를_실패한다() {
        given(scheduleRepository.findScheduleWithGroupById(anyLong())).willReturn(Optional.of(schedule));

        assertThatThrownBy(() -> attendanceService.findScheduleAttendances(user, schedule.getId()))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_MANAGER_AUTHORIZED.getMessage());
    }
}
