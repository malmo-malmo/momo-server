package com.momo.group.domain.repository;

import com.momo.group.domain.Group;
import com.momo.user.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long>, GroupRepositoryCustom {

    List<Group> findAllByManagerAndIsEnd(User manager, boolean isEnd);
}
