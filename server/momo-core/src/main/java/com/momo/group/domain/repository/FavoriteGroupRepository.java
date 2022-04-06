package com.momo.group.domain.repository;

import com.momo.group.domain.favorite.FavoriteGroup;
import com.momo.group.domain.Group;
import com.momo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteGroupRepository extends JpaRepository<FavoriteGroup, Long>, FavoriteGroupCustomRepository {

    Long countByUser(User user);

    void deleteByUserAndGroup(User user, Group group);
}
