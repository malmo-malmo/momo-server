package com.momo.unit.favorite.service;

import static com.momo.FavoriteFixture.getFavoriteGroup;
import static com.momo.FavoriteFixture.getFavoriteGroupCreateRequest;
import static com.momo.GroupFixture.getGroupWithId;
import static com.momo.UserFixture.getUserWithId;
import static com.momo.group.domain.category.Category.HOBBY;
import static com.momo.group.domain.category.Category.LIFE;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.momo.common.ServiceTest;
import com.momo.common.dto.EnumResponse;
import com.momo.favorite.dto.FavoriteCategoriesUpdateRequest;
import com.momo.favorite.dto.FavoriteGroupCreateRequest;
import com.momo.favorite.entity.FavoriteGroup;
import com.momo.favorite.repository.FavoriteGroupRepository;
import com.momo.favorite.service.FavoriteService;
import com.momo.favorite.service.impl.FavoriteServiceImpl;
import com.momo.group.domain.category.Category;
import com.momo.group.domain.Group;
import com.momo.group.domain.repository.GroupRepository;
import com.momo.user.domain.User;
import com.momo.user.domain.repository.UserRepository;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

@DisplayName("관심 서비스 테스트")
public class FavoriteServiceTest extends ServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private FavoriteGroupRepository favoriteGroupRepository;

    @Mock
    private UserRepository userRepository;

    private FavoriteService favoriteService;

    private User user;

    @BeforeEach
    void setUp() {
        favoriteService = new FavoriteServiceImpl(groupRepository, favoriteGroupRepository, userRepository);
        user = getUserWithId();
    }

    @Test
    void 관심_모임으로_등록한다() {
        Group group = getGroupWithId(user);
        FavoriteGroupCreateRequest request = getFavoriteGroupCreateRequest(group.getId());
        FavoriteGroup favoriteGroup = getFavoriteGroup(user, group);

        given(groupRepository.findById(any())).willReturn(of(group));
        given(favoriteGroupRepository.save(any())).willReturn(favoriteGroup);

        favoriteService.createFavoriteGroup(user, request);

        verify(groupRepository).findById(any());
        verify(favoriteGroupRepository).save(any());
    }

    @Test
    void 유저_관심_카테고리_정보_조회_테스트() {
        List<Category> expected = List.of(Category.HEALTH, Category.EMPLOYMENT);
        user.updateFavoriteCategories(expected);

        List<EnumResponse> actual = favoriteService.findFavoriteCategoriesByUser(user);

        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.size()).isEqualTo(expected.size()),
            () -> assertThat(actual.get(0).getCode()).isEqualTo(expected.get(0).getCode()),
            () -> assertThat(actual.get(0).getName()).isEqualTo(expected.get(0).getName()),
            () -> assertThat(actual.get(1).getCode()).isEqualTo(expected.get(1).getCode()),
            () -> assertThat(actual.get(1).getName()).isEqualTo(expected.get(1).getName())
        );
    }

    @Test
    void 유저_관심_카테고리_정보_업데이트_테스트() {
        List<Category> categories = List.of(HOBBY, LIFE);
        FavoriteCategoriesUpdateRequest request = new FavoriteCategoriesUpdateRequest(categories);

        given(userRepository.findById(any())).willReturn(of(user));

        favoriteService.updateFavoriteCategories(user, request);

        Assertions.assertAll(
            () -> assertThat(user.getFavoriteCategories().getFavoriteCategories().size()).isEqualTo(2),
            () -> assertThat(user.getFavoriteCategories().getFavoriteCategories().get(0).getCategory())
                .isEqualTo(request.getFavoriteCategories().get(0)),
            () -> assertThat(user.getFavoriteCategories().getFavoriteCategories().get(1).getCategory())
                .isEqualTo(request.getFavoriteCategories().get(1))
        );
    }
}
