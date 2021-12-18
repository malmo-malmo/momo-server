package com.momo.domain.group.domain.repository;

import com.momo.domain.group.domain.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Groups, Long>, GroupRepositoryCustom {
}