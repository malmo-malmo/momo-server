package com.momo.domain.group.repository;

import com.momo.domain.group.entity.Groups;
import com.momo.domain.group.entity.Participant;
import com.momo.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    List<Participant> findAllByGroup(Groups group);

    Boolean existsByGroupAndUser(Groups group, User user);

    void deleteByGroupAndUser(Groups group, User user);
}
