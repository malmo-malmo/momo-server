package com.momo.user.application;

import com.momo.common.dto.EnumResponse;
import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.user.application.dto.request.FavoriteCategoriesUpdateRequest;
import com.momo.user.domain.User;
import com.momo.user.domain.favorite.FavoriteCategories;
import com.momo.user.domain.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FavoriteCategoryService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<EnumResponse> findFavoriteCategoriesByUser(User loginUser) {
        FavoriteCategories favoriteCategories = loginUser.getFavoriteCategories();
        return EnumResponse.listOfFavoriteCategories(favoriteCategories);
    }

    public void updateFavoriteCategories(User loginUser, FavoriteCategoriesUpdateRequest request) {
        User user = findByUser(loginUser);
        user.updateFavoriteCategories(request.getFavoriteCategories());
    }

    private User findByUser(User user) {
        return userRepository.findById(user.getId())
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }
}
