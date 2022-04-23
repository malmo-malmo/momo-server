package com.momo.schedule.service.impl;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.domain.Group;
import com.momo.group.domain.participant.Participant;
import com.momo.group.domain.repository.ParticipantRepository;
import com.momo.schedule.dto.AttendanceCreateRequests;
import com.momo.schedule.dto.AttendanceResponse;
import com.momo.schedule.dto.AttendanceUpdateRequests;
import com.momo.schedule.entity.Attendance;
import com.momo.schedule.entity.Schedule;
import com.momo.schedule.repository.AttendanceRepository;
import com.momo.schedule.repository.ScheduleRepository;
import com.momo.schedule.service.AttendanceService;
import com.momo.user.domain.User;
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

    public void createScheduleAttendances(User user, AttendanceCreateRequests requests) {
        Schedule schedule = getScheduleById(requests.getScheduleId());
        validateGroupManager(schedule.getGroup(), user);
        schedule.updateAttendanceCheck(true);

        List<Participant> participants = participantRepository
            .findAllByIdsAndUser(requests.toParticipantIds(), schedule.getGroup());
        validateArraySize(requests.getAttendanceCreateRequests().size(), participants.size());

        int i = 0;
        List<Attendance> attendances = new ArrayList<>();
        for (Participant participant : participants) {
            Attendance attendance = requests.getAttendanceCreateRequests().get(i++).toEntity(participant, schedule);
            attendances.add(attendance);
        }

        attendanceRepository.saveAll(attendances);
    }

    public void updateScheduleAttendances(User user, AttendanceUpdateRequests requests) {
        Schedule schedule = getScheduleById(requests.getScheduleId());
        validateGroupManager(schedule.getGroup(), user);

        List<Attendance> attendances = attendanceRepository.findAllByIds(requests.toAttendanceIds());
        validateArraySize(requests.getAttendanceUpdateRequests().size(), attendances.size());

        for (int i = 0; i < attendances.size(); i++) {
            Attendance attendance = attendances.get(i);
            boolean isAttend = requests.getAttendanceUpdateRequests().get(i).isAttend();
            attendance.updateAttend(isAttend);
        }
    }

    private void validateArraySize(int reqSize, int resSize) {
        if (reqSize != resSize) {
            throw new CustomException(ErrorCode.INVALID_INDEX_NUMBER);
        }
    }

    @Transactional(readOnly = true)
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
        return scheduleRepository.findScheduleWithGroupById(scheduleId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }
}
