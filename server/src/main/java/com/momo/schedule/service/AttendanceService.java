package com.momo.schedule.service;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.domain.model.Groups;
import com.momo.group.domain.repository.GroupRepository;
import com.momo.schedule.controller.dto.AttendanceCreateRequests;
import com.momo.schedule.domain.model.Attendance;
import com.momo.schedule.domain.model.Schedule;
import com.momo.schedule.domain.repository.AttendanceRepository;
import com.momo.schedule.domain.repository.ScheduleRepository;
import com.momo.user.domain.model.User;
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
        schedule.checkAttendance();
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
