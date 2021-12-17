package com.momo.domain.schedule.service;

import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.domain.model.Groups;
import com.momo.domain.group.domain.repository.GroupRepository;
import com.momo.domain.schedule.domain.model.Attendance;
import com.momo.domain.schedule.domain.model.Schedule;
import com.momo.domain.schedule.domain.repository.AttendanceRepository;
import com.momo.domain.schedule.domain.repository.ScheduleRepository;
import com.momo.domain.schedule.dto.AttendanceCreateRequests;
import com.momo.domain.user.domain.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AttendanceService {

    private final GroupRepository groupRepository;

    private final ScheduleRepository scheduleRepository;

    private final AttendanceRepository attendanceRepository;

    public void create(User user, AttendanceCreateRequests requests) {
        Groups group = getGroupById(requests.getGroupId());
        validateGroupManager(group, user);
        Schedule schedule = getScheduleById(requests.getScheduleId());
        schedule.updateAttendanceCheck(true);
        List<Attendance> attendances = Attendance.create(requests.toEntities(), group, schedule);
        attendanceRepository.saveAll(attendances);
    }

    public Groups getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    public void validateGroupManager(Groups group, User user) {
        if (!group.isManager(user)) {
            throw new CustomException(ErrorCode.GROUP_MANAGER_AUTHORIZED);
        }
    }

    public Schedule getScheduleById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }
}
