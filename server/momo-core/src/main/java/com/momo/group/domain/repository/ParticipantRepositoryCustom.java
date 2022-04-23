package com.momo.group.domain.repository;

import com.momo.group.domain.Group;
import com.momo.group.domain.participant.Participant;
import com.momo.management.dto.ParticipationGroupCardResponse;
import com.momo.user.domain.User;
import java.util.List;

public interface ParticipantRepositoryCustom {

    List<ParticipationGroupCardResponse> findParticipationGroupsByUser(User user);

    List<Participant> findAllWithNotManagingGroupByUser(User user);

    List<Participant> findAllByIdsAndUser(List<Long> participantIds, Group group);
}
