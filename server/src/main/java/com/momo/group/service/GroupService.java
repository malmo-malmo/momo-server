package com.momo.group.service;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.controller.dto.GroupCreateRequest;
import com.momo.group.controller.dto.GroupResponse;
import com.momo.group.domain.model.Groups;
import com.momo.group.domain.model.Participant;
import com.momo.group.domain.repository.GroupRepository;
import com.momo.group.domain.repository.ParticipantRepository;
import com.momo.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    private final ParticipantRepository participantRepository;

    public Long create(User user, GroupCreateRequest groupCreateRequest) {
        Groups group = groupRepository.save(Groups.create(user, groupCreateRequest.toEntity()));
        participantRepository.save(Participant.create(user, group));
        return group.getId();
    }

    @Transactional(readOnly = true)
    public GroupResponse find(User user, Long groupId) {
        Groups group = getGroupById(groupId);
        if (group.isManager(user)) {
            return GroupResponse.of(group, true, true);
        }
        return GroupResponse.of(group, false, participantRepository.existsByUserAndGroup(user, group));
    }

    public Groups getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }
}
