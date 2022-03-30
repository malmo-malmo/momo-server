package com.momo.favorite.repository;

import com.momo.favorite.entity.FavoriteGroup;
import com.momo.group.entity.Group;
import com.momo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteGroupRepository extends JpaRepository<FavoriteGroup, Long>, FavoriteGroupCustomRepository {

    Long countByUser(User user);

    void deleteByUserAndGroup(User user, Group group);
}
