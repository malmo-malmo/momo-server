package com.momo.domain.schedule.service.impl;

import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.entity.Participant;
import com.momo.domain.group.repository.ParticipantRepository;
import com.momo.domain.schedule.dto.AttendanceCreateRequest;
import com.momo.domain.schedule.dto.AttendanceCreateRequests;
import com.momo.domain.schedule.dto.AttendanceResponse;
import com.momo.domain.schedule.dto.AttendanceUpdateRequest;
import com.momo.domain.schedule.dto.AttendanceUpdateRequests;
import com.momo.domain.schedule.entity.Attendance;
import com.momo.domain.schedule.entity.Schedule;
import com.momo.domain.schedule.repository.AttendanceRepository;
import com.momo.domain.schedule.repository.ScheduleRepository;
import com.momo.domain.schedule.service.AttendanceService;
import com.momo.domain.user.entity.User;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final ScheduleRepository scheduleRepository;

    private final AttendanceRepository attendanceRepository;

    private final ParticipantRepository participantRepository;

    public void creates(User user, AttendanceCreateRequests requests) {
        Schedule schedule = getScheduleById(requests.getScheduleId());
        schedule.updateAttendanceCheck(true);

        List<Attendance> attendances = new ArrayList<>();
        for (AttendanceCreateRequest request : requests.getAttendanceCreateRequests()) {
            Participant participant = getParticipantById(request.getParticipantId());
            validateGroupManager(participant.getGroup(), user);

            Attendance attendance = request.toEntity(participant, schedule);
            attendances.add(attendance);
        }
        attendanceRepository.saveAll(attendances);
    }

    public void updateScheduleAttendances(User user, AttendanceUpdateRequests requests) {
        Schedule schedule = getScheduleById(requests.getScheduleId());
        for (AttendanceUpdateRequest request : requests.getAttendanceUpdateRequests()) {
            validateGroupManager(schedule.getGroup(), user);
            this.update(schedule, request);
        }
    }

    private void update(Schedule schedule, AttendanceUpdateRequest request) {
        Attendance attendance = getAttendanceById(request.getAttendanceId());
        if (schedule.isSameSchedule(attendance.getSchedule())) {
            attendance.updateAttend(request.isAttend());
        }
    }

    public List<AttendanceResponse> findScheduleAttendances(User user, Long scheduleId) {
        Schedule schedule = getScheduleById(scheduleId);
        validateGroupManager(schedule.getGroup(), user);

        List<Attendance> attendances = attendanceRepository.findBySchedule(schedule);
        return AttendanceResponse.listOf(attendances);
    }

    private void validateGroupManager(Group group, User user) {
        if (!group.isManager(user)) {
            throw new CustomException(ErrorCode.GROUP_MANAGER_AUTHORIZED);
        }
    }

    private Schedule getScheduleById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    private Attendance getAttendanceById(Long attendanceId) {
        return attendanceRepository.findById(attendanceId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    private Participant getParticipantById(Long participantId) {
        return participantRepository.findById(participantId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }
}
