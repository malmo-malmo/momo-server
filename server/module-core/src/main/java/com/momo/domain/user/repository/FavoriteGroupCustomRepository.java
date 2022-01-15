package com.momo.domain.user.repository;

import com.momo.domain.user.dto.FavoriteGroupCardResponse;
import com.momo.domain.user.entity.User;
import java.util.List;

public interface FavoriteGroupCustomRepository {

    List<FavoriteGroupCardResponse> findAllByUserOrderByCreatedDateDesc(User user);
}
