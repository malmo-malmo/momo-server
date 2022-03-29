package com.momo.favorite.entity;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.entity.Category;
import com.momo.user.entity.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FavoriteCategories {

    @BatchSize(size = 10)
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<FavoriteCategory> favoriteCategories = new ArrayList<>();

    public static FavoriteCategories empty() {
        return new FavoriteCategories();
    }

    public List<FavoriteCategory> getFavoriteCategories() {
        return Collections.unmodifiableList(favoriteCategories);
    }

    public void updateAll(User loginUser, List<Category> categories) {
        validateCategories(categories);
        if (favoriteCategories.size() > 0) {
            deleteAll();
        }
        for (Category category : categories) {
            FavoriteCategory favoriteCategory = FavoriteCategory.create(loginUser, category);
            favoriteCategories.add(favoriteCategory);
        }
    }

    private void validateCategories(List<Category> categories) {
        if (Objects.isNull(categories) || categories.size() < 1) {
            throw new CustomException(ErrorCode.INVALID_FAVORITE_CATEGORY);
        }
    }

    private void deleteAll() {
        favoriteCategories.clear();
    }

    public List<Category> toCategories() {
        return favoriteCategories.stream()
            .map(FavoriteCategory::getCategory)
            .collect(Collectors.toList());
    }
}
