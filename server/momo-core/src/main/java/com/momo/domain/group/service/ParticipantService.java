package com.momo.domain.group.service;

import com.momo.domain.group.dto.ParticipantResponse;
import com.momo.domain.user.domain.User;
import java.util.List;

public interface ParticipantService {

    List<ParticipantResponse> findByGroupId(User user, Long groupId);

    void withdrawByGroupId(User user, Long groupId);

    Long applyParticipantByGroup(User user, Long groupId);
}
