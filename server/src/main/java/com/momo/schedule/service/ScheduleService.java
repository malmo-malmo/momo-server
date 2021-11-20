package com.momo.schedule.service;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.domain.model.Groups;
import com.momo.group.domain.repository.GroupRepository;
import com.momo.group.domain.repository.ParticipantRepository;
import com.momo.schedule.controller.dto.GroupScheduleResponse;
import com.momo.schedule.controller.dto.GroupSchedulesResponse;
import com.momo.schedule.controller.dto.ScheduleCreateRequest;
import com.momo.schedule.domain.model.Schedule;
import com.momo.schedule.domain.repository.ScheduleRepository;
import com.momo.user.domain.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final GroupRepository groupRepository;

    private final ParticipantRepository participantRepository;

    public Long create(User user, ScheduleCreateRequest request) {
        Groups group = getGroupById(request.getGroupId());
        if (!group.isManager(user)) {
            throw new CustomException(ErrorCode.GROUP_SCHEDULE_UNAUTHORIZED);
        }
        return scheduleRepository.save(Schedule.create(request.toEntity(), group, user)).getId();
    }

    @Transactional(readOnly = true)
    public GroupSchedulesResponse findPageByGroupAndUser(User user, Long groupId, int page, int size) {
        Groups group = getGroupById(groupId);
        validateIsGroupParticipant(user, group);
        List<GroupScheduleResponse> responses = scheduleRepository
            .findAllByGroupAndUserOrderByCreatedDateDesc(group, user, PageRequest.of(page, size));
        return GroupSchedulesResponse.of(responses, group.isManager(user));
    }

    public Groups getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    public void validateIsGroupParticipant(User user, Groups group) {
        if (!participantRepository.existsByUserAndGroup(user, group)) {
            throw new CustomException(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED);
        }
    }
}
