package com.momo.domain.favorite.service;

import com.momo.domain.common.dto.EnumResponse;
import com.momo.domain.favorite.dto.FavoriteCategoriesUpdateRequest;
import com.momo.domain.favorite.dto.FavoriteGroupCardResponse;
import com.momo.domain.favorite.dto.FavoriteGroupCountResponse;
import com.momo.domain.favorite.dto.FavoriteGroupCreateRequest;
import com.momo.domain.user.domain.User;
import java.util.List;

public interface FavoriteService {

    void createFavoriteGroup(User user, FavoriteGroupCreateRequest request);

    FavoriteGroupCountResponse countFavoriteGroupsByUser(User user);

    List<FavoriteGroupCardResponse> findFavoriteGroupsByUser(User user);

    List<EnumResponse> findFavoriteCategoriesByUser(User loginUser);

    void updateFavoriteCategories(User loginUser, FavoriteCategoriesUpdateRequest request);

    void deleteFavoriteGroupByUserAndGroupId(User loginUser, Long groupId);
}
