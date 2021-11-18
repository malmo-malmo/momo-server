package com.momo.group.domain.repository;

import com.momo.group.domain.model.Groups;
import com.momo.group.domain.model.Participant;
import com.momo.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    Boolean existsByUserAndGroup(User user, Groups group);
}
