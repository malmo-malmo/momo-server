package com.momo.domain.group.domain.repository;

import com.momo.domain.group.domain.model.Groups;
import com.momo.domain.group.domain.model.Participant;
import com.momo.domain.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    List<Participant> findAllByGroup(Groups group);

    Boolean existsByGroupAndUser(Groups group, User user);

    void deleteByGroupAndUser(Groups group, User user);
}
