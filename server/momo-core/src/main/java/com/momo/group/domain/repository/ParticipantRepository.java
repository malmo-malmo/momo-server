package com.momo.group.domain.repository;

import com.momo.group.domain.Group;
import com.momo.group.domain.participant.Participant;
import com.momo.user.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long>, ParticipantRepositoryCustom {

    List<Participant> findAllByGroup(Group group);

    Boolean existsByGroupAndUser(Group group, User user);

    void deleteByGroupAndUser(Group group, User user);

    Long countAllByUser(User user);
}
