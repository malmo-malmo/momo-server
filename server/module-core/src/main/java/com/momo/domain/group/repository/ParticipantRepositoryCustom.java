package com.momo.domain.group.repository;

import com.momo.domain.group.entity.Group;
import com.momo.domain.group.entity.Participant;
import com.momo.domain.management.dto.ParticipatingGroupCardResponse;
import com.momo.domain.schedule.entity.Attendance;
import com.momo.domain.user.entity.User;
import java.util.List;

public interface ParticipantRepositoryCustom {

    List<ParticipatingGroupCardResponse> findParticipatingGroupsByUser(User user);

    List<Participant> findAllByIdsAndUser(List<Long> participantIds, User manager);
}
