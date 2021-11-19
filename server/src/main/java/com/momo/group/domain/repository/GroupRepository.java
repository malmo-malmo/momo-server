package com.momo.group.domain.repository;

import com.momo.group.domain.model.Groups;
import com.momo.user.domain.model.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Groups, Long>, GroupRepositoryCustom {

    Page<Groups> findAllByUniversityOrderByCreatedDateDesc(String university, Pageable pageable);

    Page<Groups> findAllByLocationOrderByCreatedDateDesc(Location location, Pageable pageable);
}
