package com.momo.favorite.repository;

import com.momo.favorite.dto.FavoriteGroupCardResponse;
import com.momo.user.domain.User;
import java.util.List;

public interface FavoriteGroupCustomRepository {

    List<FavoriteGroupCardResponse> findAllByUserOrderByCreatedDateDesc(User user);
}
