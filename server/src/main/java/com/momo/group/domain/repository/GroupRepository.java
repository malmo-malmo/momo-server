package com.momo.group.domain.repository;

import com.momo.group.domain.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Groups, Long>, GroupRepositoryCustom {

}
