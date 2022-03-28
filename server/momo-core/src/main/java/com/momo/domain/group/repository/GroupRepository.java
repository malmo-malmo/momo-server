package com.momo.domain.group.repository;

import com.momo.domain.group.entity.Group;
import com.momo.domain.user.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long>, GroupRepositoryCustom {

    List<Group> findAllByManagerAndIsEnd(User manager, boolean isEnd);
}
