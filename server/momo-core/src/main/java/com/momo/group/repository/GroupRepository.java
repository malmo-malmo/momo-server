package com.momo.group.repository;

import com.momo.group.entity.Group;
import com.momo.user.domain.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long>, GroupRepositoryCustom {

    List<Group> findAllByManagerAndIsEnd(User manager, boolean isEnd);
}
