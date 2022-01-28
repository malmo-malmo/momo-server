package com.momo.domain.schedule.service.impl;

import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.schedule.dto.AttendanceCreateRequest;
import com.momo.domain.schedule.dto.AttendanceCreateRequests;
import com.momo.domain.schedule.dto.AttendanceResponse;
import com.momo.domain.schedule.dto.AttendanceUpdateRequest;
import com.momo.domain.schedule.entity.Attendance;
import com.momo.domain.schedule.entity.Schedule;
import com.momo.domain.schedule.repository.AttendanceRepository;
import com.momo.domain.schedule.repository.ScheduleRepository;
import com.momo.domain.schedule.service.AttendanceService;
import com.momo.domain.user.entity.User;
import com.momo.domain.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final GroupRepository groupRepository;

    private final ScheduleRepository scheduleRepository;

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;

    public void create(User user, AttendanceCreateRequests requests) {
        Group group = getGroupById(requests.getGroupId());
        validateGroupManager(group, user);
        Schedule schedule = getScheduleById(requests.getScheduleId());
        schedule.updateAttendanceCheck(true);

        List<Attendance> attendances = new ArrayList<>();
        for (AttendanceCreateRequest request : requests.getAttendanceCreateRequests()) {
            Long userId = request.getUserId();
            User attendanceUser = userRepository.findById(userId).orElseThrow();
            Attendance attendance = request.toEntity(attendanceUser, group, schedule);
            attendances.add(attendance);
        }
        attendanceRepository.saveAll(attendances);
    }

    public void update(User user, AttendanceUpdateRequest request) {
        Attendance attendance = attendanceRepository.findByUser(user)
            .orElseThrow();
        validateGroupManager(attendance.getGroup(), user);

        attendance.updateAttend(request.isAttend());
    }

    public List<AttendanceResponse> findGroupAttendances(User user, Long groupId) {
        Group group = groupRepository.findById(groupId)
            .orElseThrow();
        validateGroupManager(group, user);

        List<Attendance> attendances = attendanceRepository.findByGroup(group);

        return attendances.stream().map(AttendanceResponse::new).collect(Collectors.toList());
    }

    private Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
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
}
