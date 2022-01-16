package com.momo.domain.group.repository;

import com.momo.domain.user.dto.ParticipatingGroupCardResponse;
import com.momo.domain.user.entity.User;
import java.util.List;

public interface ParticipantRepositoryCustom {

    List<ParticipatingGroupCardResponse> findParticipatingGroupsByUser(User user);
}
