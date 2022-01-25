package com.momo.domain.group.service.impl;

import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.entity.Participant;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.group.repository.ParticipantRepository;
import com.momo.domain.group.dto.ParticipantResponse;
import com.momo.domain.group.service.ParticipantService;
import com.momo.domain.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;

    private final GroupRepository groupRepository;

    @Transactional(readOnly = true)
    public List<ParticipantResponse> findByGroupId(User user, Long groupId) {
        Group group = getGroupById(groupId);
        validateGroupManager(group, user);
        List<Participant> participants = participantRepository.findAllByGroup(group);
        for (Participant participant : participants) {
            participant.calculateAttendanceRate();
        }
        return ParticipantResponse.listOf(participants);
    }

    public void validateGroupManager(Group group, User user) {
        if (!group.isManager(user)) {
            throw new CustomException(ErrorCode.GROUP_MANAGER_AUTHORIZED);
        }
    }

    public void withdrawByGroupId(User user, Long groupId) {
        Group group = getGroupById(groupId);
        validateNotGroupManager(group, user);
        participantRepository.deleteByGroupAndUser(group, user);
    }

    public Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    public void validateNotGroupManager(Group group, User user) {
        if (group.isManager(user)) {
            throw new CustomException(ErrorCode.GROUP_MANAGER_WITHDRAW_NOT_ALLOW);
        }
    }

    /*
    테스트를 위해 임시로 만든 API
    */
    public void applyParticipantByGroup(User user, Long groupId) {
        Group group = getGroupById(groupId);
        participantRepository.save(Participant.create(user, group));
    }

}