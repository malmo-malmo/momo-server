package com.momo.schedule.service;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.domain.model.Groups;
import com.momo.group.domain.repository.GroupRepository;
import com.momo.schedule.controller.dto.ScheduleCreateRequest;
import com.momo.schedule.domain.model.Schedule;
import com.momo.schedule.domain.repository.ScheduleRepository;
import com.momo.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final GroupRepository groupRepository;

    public Long create(User user, ScheduleCreateRequest scheduleCreateRequest) {
        Groups group = getGroupById(scheduleCreateRequest.getGroupId());
        if (!group.isManager(user)) {
            throw new CustomException(ErrorCode.GROUP_SCHEDULE_UNAUTHORIZED);
        }
        return scheduleRepository.save(Schedule.create(scheduleCreateRequest.toEntity(), group)).getId();
    }

    public Groups getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

}
