package com.momo.domain.group.repository;

import com.momo.domain.group.entity.Participant;
import com.momo.domain.management.dto.ParticipationGroupCardResponse;
import com.momo.domain.user.entity.User;
import java.util.List;

public interface ParticipantRepositoryCustom {

    List<ParticipationGroupCardResponse> findParticipationGroupsByUser(User user);

    List<Participant> findAllWithNotManagingGroupByUser(User user);
}
