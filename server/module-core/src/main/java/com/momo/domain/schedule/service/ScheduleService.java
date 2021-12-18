package com.momo.domain.schedule.service;

import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.domain.model.Groups;
import com.momo.domain.group.domain.repository.GroupRepository;
import com.momo.domain.group.domain.repository.ParticipantRepository;
import com.momo.domain.schedule.domain.model.Schedule;
import com.momo.domain.schedule.domain.repository.ScheduleRepository;
import com.momo.domain.schedule.dto.*;
import com.momo.domain.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final GroupRepository groupRepository;
    private final ParticipantRepository participantRepository;

    public Long create(User user, ScheduleCreateRequest request) {
        Groups group = getGroupById(request.getGroupId());
        validateGroupManager(group, user);
        return scheduleRepository.save(Schedule.create(request.toEntity(), group, user)).getId();
    }

    public void validateGroupManager(Groups group, User user) {
        if (!group.isManager(user)) {
            throw new CustomException(ErrorCode.GROUP_MANAGER_AUTHORIZED);
        }
    }

    @Transactional(readOnly = true)
    public GroupScheduleResponses findPageByUserAndGroupId(User user, GroupSchedulesRequest request) {
        Groups group = getGroupById(request.getGroupId());
        validateParticipant(group, user);
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());
        List<GroupScheduleResponse> responses = scheduleRepository
            .findAllByGroupAndUserOrderByCreatedDateDesc(group, user.getId(), pageRequest);
        return GroupScheduleResponses.of(responses, group.getManager().getId());
    }

    public void validateParticipant(Groups group, User user) {
        if (!participantRepository.existsByGroupAndUser(group, user)) {
            throw new CustomException(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED);
        }
    }

    @Transactional(readOnly = true)
    public List<UserScheduleResponse> findPageByUserAndSearchDate(User user, UserSchedulesRequest request) {
        LocalDateTime searchStartDateTime = request.getSearchStartDate().atStartOfDay();
        LocalDateTime searchEndDateTime = request.getSearchEndDate().plusDays(1).atStartOfDay();
        List<Schedule> schedules = scheduleRepository
            .findAllByStartDateTimeBetween(searchStartDateTime, searchEndDateTime, user);
        return UserScheduleResponse.listOf(schedules);
    }

    public Groups getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }
}
