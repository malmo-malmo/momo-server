package com.momo.group.domain.repository;

import com.momo.group.application.dto.response.FavoriteGroupCardResponse;
import com.momo.user.domain.User;
import java.util.List;

public interface FavoriteGroupCustomRepository {

    List<FavoriteGroupCardResponse> findAllByUserOrderByCreatedDateDesc(User user);
}
