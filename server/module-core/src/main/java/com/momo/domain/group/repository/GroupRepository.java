package com.momo.domain.group.repository;

import com.momo.domain.group.entity.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Groups, Long>, GroupRepositoryCustom {
}