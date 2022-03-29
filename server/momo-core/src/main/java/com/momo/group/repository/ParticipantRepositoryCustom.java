package com.momo.group.repository;

import com.momo.group.entity.Group;
import com.momo.group.entity.Participant;
import com.momo.management.dto.ParticipationGroupCardResponse;
import com.momo.user.entity.User;
import java.util.List;

public interface ParticipantRepositoryCustom {

    List<ParticipationGroupCardResponse> findParticipationGroupsByUser(User user);

    List<Participant> findAllWithNotManagingGroupByUser(User user);

    List<Participant> findAllByIdsAndUser(List<Long> participantIds, Group group);
}
