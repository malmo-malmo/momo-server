package com.momo.group.service;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.controller.dto.ParticipantResponse;
import com.momo.group.domain.model.Groups;
import com.momo.group.domain.model.Participant;
import com.momo.group.domain.repository.GroupRepository;
import com.momo.group.domain.repository.ParticipantRepository;
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

    @Transactional(readOnly = true)
    public List<ParticipantResponse> findByGroupId(User user, Long groupId) {
        Groups group = getGroupById(groupId);
        validateNotGroupManager(group, user);
        List<Participant> participants = participantRepository.findAllByGroup(group);

        for (Participant participant : participants) {
            participant.calculateAttendanceRate();
        }

        return ParticipantResponse.listOf(participants);
    }

    public void validateNotGroupManager(Groups group, User user) {
        if (group.isNotManager(user)) {
            throw new CustomException(ErrorCode.GROUP_PARTICIPANTS_UNAUTHORIZED);
        }
    }

    public void deleteByGroupId(User user, Long groupId) {
        Groups group = getGroupById(groupId);
        validateGroupManager(group, user);
        participantRepository.deleteByUserAndGroup(user, group);
    }

    public Groups getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    public void validateGroupManager(Groups group, User user) {
        if (group.isManager(user)) {
            throw new CustomException(ErrorCode.GROUP_MANAGER_WITHDRAW_NOT_ALLOW);
        }
    }

    /*
    테스트를 위해 임시로 만든 API
    */
    public void applyParticipantByGroup(User user, Long groupId) {
        Groups group = getGroupById(groupId);
        participantRepository.save(Participant.create(user, group));
    }

}
