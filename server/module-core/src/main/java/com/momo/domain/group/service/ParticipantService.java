package com.momo.domain.group.service;

import com.momo.domain.group.dto.ParticipantResponse;
import com.momo.domain.group.entity.Group;
import com.momo.domain.user.entity.User;
import java.util.List;

public interface ParticipantService {

    List<ParticipantResponse> findByGroupId(User user, Long groupId);

    void validateGroupManager(Group group, User user);

    void withdrawByGroupId(User user, Long groupId);

    Group getGroupById(Long groupId);

    void validateNotGroupManager(Group group, User user);

    void applyParticipantByGroup(User user, Long groupId);
}
