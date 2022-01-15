package com.momo.domain.user.repository;

import com.momo.domain.user.entity.FavoriteGroup;
import com.momo.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteGroupRepository extends JpaRepository<FavoriteGroup, Long>, FavoriteGroupCustomRepository {

    Long countByUser(User user);
}
