package com.momo.domain.schedule.service.impl;

import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.dto.GroupResponse;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.group.repository.ParticipantRepository;
import com.momo.domain.schedule.dto.GroupScheduleResponse;
import com.momo.domain.schedule.dto.GroupScheduleResponses;
import com.momo.domain.schedule.dto.GroupSchedulesRequest;
import com.momo.domain.schedule.dto.ScheduleCreateRequest;
import com.momo.domain.schedule.dto.UserScheduleResponse;
import com.momo.domain.schedule.dto.UserSchedulesRequest;
import com.momo.domain.schedule.entity.Schedule;
import com.momo.domain.schedule.repository.ScheduleRepository;
import com.momo.domain.schedule.service.ScheduleService;
import com.momo.domain.user.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final GroupRepository groupRepository;

    private final ParticipantRepository participantRepository;

    public GroupScheduleResponse create(User user, ScheduleCreateRequest request) {
        Group group = getGroupById(request.getGroupId());
        validateGroupManager(group, user);
        Schedule schedule = Schedule.create(request.toEntity(), group, user);
        scheduleRepository.save(schedule);

        return scheduleRepository.findGroupResponseById(schedule.getId(), user.getId());
    }

    @Transactional(readOnly = true)
    public GroupScheduleResponses findPageByUserAndGroupId(User user, GroupSchedulesRequest request) {
        Group group = getGroupById(request.getGroupId());
        validateParticipant(group, user);
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());
        List<GroupScheduleResponse> responses = scheduleRepository
            .findAllByGroupAndUserOrderByCreatedDateDesc(group, user.getId(), pageRequest);
        return GroupScheduleResponses.of(responses, group.getManager().getId());
    }

    @Transactional(readOnly = true)
    public List<UserScheduleResponse> findPageByUserAndSearchDate(User user, UserSchedulesRequest request) {
        LocalDateTime searchStartDateTime = request.getSearchStartDate().atStartOfDay();
        LocalDateTime searchEndDateTime = request.getSearchEndDate().plusDays(1).atStartOfDay();
        List<Schedule> schedules = scheduleRepository
            .findAllByStartDateTimeBetween(searchStartDateTime, searchEndDateTime, user);
        return UserScheduleResponse.listOf(schedules);
    }

    public Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    public void validateParticipant(Group group, User user) {
        if (!participantRepository.existsByGroupAndUser(group, user)) {
            throw new CustomException(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED);
        }
    }

    public void validateGroupManager(Group group, User user) {
        if (!group.isManager(user)) {
            throw new CustomException(ErrorCode.GROUP_MANAGER_AUTHORIZED);
        }
    }
}