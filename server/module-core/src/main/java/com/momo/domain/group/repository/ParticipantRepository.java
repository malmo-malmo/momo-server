package com.momo.domain.group.repository;

import com.momo.domain.group.entity.Group;
import com.momo.domain.group.entity.Participant;
import com.momo.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    List<Participant> findAllByGroup(Group group);

    Boolean existsByGroupAndUser(Group group, User user);

    void deleteByGroupAndUser(Group group, User user);
}
