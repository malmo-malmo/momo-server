package com.momo.domain.favorite.repository;

import com.momo.domain.favorite.dto.FavoriteGroupCardResponse;
import com.momo.domain.user.entity.User;
import java.util.List;

public interface FavoriteGroupCustomRepository {

    List<FavoriteGroupCardResponse> findAllByUserOrderByCreatedDateDesc(User user);
}
