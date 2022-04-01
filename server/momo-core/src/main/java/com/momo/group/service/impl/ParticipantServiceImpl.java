package com.momo.group.service.impl;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.dto.ParticipantResponse;
import com.momo.group.entity.Group;
import com.momo.group.entity.Participant;
import com.momo.group.repository.GroupRepository;
import com.momo.group.repository.ParticipantRepository;
import com.momo.group.service.ParticipantService;
import com.momo.user.domain.model.User;
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
        return ParticipantResponse.listOf(participants);
    }

    private void validateGroupManager(Group group, User user) {
        if (!group.isManager(user)) {
            throw new CustomException(ErrorCode.GROUP_MANAGER_AUTHORIZED);
        }
    }

    public void withdrawByGroupId(User user, Long groupId) {
        Group group = getGroupById(groupId);
        validateNotGroupManager(group, user);
        participantRepository.deleteByGroupAndUser(group, user);
    }

    private Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    private void validateNotGroupManager(Group group, User user) {
        if (group.isManager(user)) {
            throw new CustomException(ErrorCode.GROUP_MANAGER_WITHDRAW_NOT_ALLOW);
        }
    }

    /*
    테스트를 위해 임시로 만든 API
    */
    public Long applyParticipantByGroup(User user, Long groupId) {
        Group group = getGroupById(groupId);
        return participantRepository.save(Participant.create(user, group)).getId();
    }
}
