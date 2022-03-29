package com.momo.group.service;

import com.momo.group.dto.ParticipantResponse;
import com.momo.user.entity.User;
import java.util.List;

public interface ParticipantService {

    List<ParticipantResponse> findByGroupId(User user, Long groupId);

    void withdrawByGroupId(User user, Long groupId);

    Long applyParticipantByGroup(User user, Long groupId);
}
