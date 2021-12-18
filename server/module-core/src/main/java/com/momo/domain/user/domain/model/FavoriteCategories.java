package com.momo.domain.user.domain.model;

import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.domain.model.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FavoriteCategories {

    @BatchSize(size = 10)
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<FavoriteCategory> favoriteCategories = new ArrayList<>();

    public static FavoriteCategories empty() {
        return new FavoriteCategories();
    }

    public void updateAll(User user, List<Category> categories) {
        if (favoriteCategories.size() > 0) {
            deleteAll();
        }
        validateCategories(categories);
        for (Category category : categories) {
            FavoriteCategory favoriteCategory = FavoriteCategory.create(user, category);
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
