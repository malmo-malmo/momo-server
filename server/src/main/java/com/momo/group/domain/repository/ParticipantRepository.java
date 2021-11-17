package com.momo.group.domain.repository;

import com.momo.group.domain.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

}
