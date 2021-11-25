package com.momo.group.service;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.controller.dto.ParticipantResponse;
import com.momo.group.domain.model.Groups;
import com.momo.group.domain.model.Participant;
import com.momo.group.domain.repository.GroupRepository;
import com.momo.group.domain.repository.ParticipantRepository;
import com.momo.schedule.domain.repository.ScheduleRepository;
import com.momo.user.domain.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    private final GroupRepository groupRepository;

    private final ScheduleRepository scheduleRepository;

    @Transactional(readOnly = true)
    public List<ParticipantResponse> findParticipantsByGroup(User user, Long groupId) {
        Groups group = getGroupById(groupId);
        validateIsGroupManager(group, user);
        Long scheduleCnt = scheduleRepository.countByGroupAndIsAttendanceCheck(group, true);
        return participantRepository.findParticipantAndAttendanceRateByGroup(group, scheduleCnt);
    }

    public void validateIsGroupManager(Groups group, User user) {
        if (!group.isManager(user)) {
            throw new CustomException(ErrorCode.GROUP_PARTICIPANTS_UNAUTHORIZED);
        }
    }

    public void applyParticipantByGroup(User user, Long groupId) {
        Groups group = getGroupById(groupId);
        participantRepository.save(Participant.create(user, group));
    }

    public Groups getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }
}
