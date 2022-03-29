package com.momo.favorite.service;

import com.momo.common.dto.EnumResponse;
import com.momo.favorite.dto.FavoriteCategoriesUpdateRequest;
import com.momo.favorite.dto.FavoriteGroupCardResponse;
import com.momo.favorite.dto.FavoriteGroupCountResponse;
import com.momo.favorite.dto.FavoriteGroupCreateRequest;
import com.momo.user.entity.User;
import java.util.List;

public interface FavoriteService {

    void createFavoriteGroup(User user, FavoriteGroupCreateRequest request);

    FavoriteGroupCountResponse countFavoriteGroupsByUser(User user);

    List<FavoriteGroupCardResponse> findFavoriteGroupsByUser(User user);

    List<EnumResponse> findFavoriteCategoriesByUser(User loginUser);

    void updateFavoriteCategories(User loginUser, FavoriteCategoriesUpdateRequest request);

    void deleteFavoriteGroupByUserAndGroupId(User loginUser, Long groupId);
}
