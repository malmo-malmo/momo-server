package com.momo.domain.favorite.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.favorite.entity.FavoriteCategories;
import com.momo.group.entity.Category;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("FavoriteCategories POJO 테스트")
public class FavoriteCategoriesTest {

    @Test
    void 유저_관심_카테고리_생성_테스트() {
        FavoriteCategories actual = FavoriteCategories.empty();
        assertThat(actual).isNotNull();
    }

    @Test
    void 비어있는_유저_관심_카테고리_업데이트_테스트() {
        FavoriteCategories actual = FavoriteCategories.empty();

        List<Category> expected = List.of(Category.HEALTH, Category.RICE);
        actual.updateAll(null, expected);

        Assertions.assertAll(
            () -> assertThat(expected.size()).isEqualTo(actual.getFavoriteCategories().size()),
            () -> assertThat(expected.get(0)).isEqualTo(actual.getFavoriteCategories().get(0).getCategory()),
            () -> assertThat(expected.get(1)).isEqualTo(actual.getFavoriteCategories().get(1).getCategory())
        );
    }

    @Test
    void 유저_관심_카테고리_업데이트_테스트() {
        FavoriteCategories actual = FavoriteCategories.empty();
        actual.updateAll(null, List.of(Category.HEALTH, Category.RICE));

        List<Category> expected = List.of(Category.HOBBY);
        actual.updateAll(null, expected);

        Assertions.assertAll(
            () -> assertThat(expected.size()).isEqualTo(actual.getFavoriteCategories().size()),
            () -> assertThat(expected.get(0)).isEqualTo(actual.getFavoriteCategories().get(0).getCategory())
        );
    }

    @Test
    void 유저_관심_카테고리를_업데이트할_때_카테고리가_널이면_테스트를_실패한다() {
        FavoriteCategories favoriteCategories = FavoriteCategories.empty();
        assertThatThrownBy(() -> favoriteCategories.updateAll(null, null))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.INVALID_FAVORITE_CATEGORY.getMessage());
    }

    @Test
    void 유저_관심_카테고리를_업데이트할_때_카테고리가_비어있으면_테스트를_실패한다() {
        FavoriteCategories favoriteCategories = FavoriteCategories.empty();
        assertThatThrownBy(() -> favoriteCategories.updateAll(null, Collections.emptyList()))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.INVALID_FAVORITE_CATEGORY.getMessage());
    }

    @Test
    void 관심_카테고리_엔티티에서_카테고리으로_변경_테스트() {
        FavoriteCategories favoriteCategories = FavoriteCategories.empty();
        List<Category> actual = List.of(Category.HEALTH, Category.RICE);
        favoriteCategories.updateAll(null, actual);

        List<Category> expected = favoriteCategories.toCategories();

        Assertions.assertAll(
            () -> assertThat(expected.size()).isEqualTo(actual.size()),
            () -> assertThat(expected.get(0)).isEqualTo(actual.get(0)),
            () -> assertThat(expected.get(1)).isEqualTo(actual.get(1))
        );
    }
}
