package com.momo.domain.schedule.service;

import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.schedule.entity.Attendance;
import com.momo.domain.schedule.entity.Schedule;
import com.momo.domain.schedule.repository.AttendanceRepository;
import com.momo.domain.schedule.repository.ScheduleRepository;
import com.momo.domain.schedule.dto.AttendanceCreateRequests;
import com.momo.domain.user.entity.User;
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
        Group group = getGroupById(requests.getGroupId());
        validateGroupManager(group, user);
        Schedule schedule = getScheduleById(requests.getScheduleId());
        schedule.updateAttendanceCheck(true);
        List<Attendance> attendances = Attendance.createAttendances(requests.toEntities(), group, schedule);
        attendanceRepository.saveAll(attendances);
    }

    public Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    public void validateGroupManager(Group group, User user) {
        if (!group.isManager(user)) {
            throw new CustomException(ErrorCode.GROUP_MANAGER_AUTHORIZED);
        }
    }

    public Schedule getScheduleById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }
}
