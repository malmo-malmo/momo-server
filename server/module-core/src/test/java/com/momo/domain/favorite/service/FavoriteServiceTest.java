package com.momo.domain.favorite.service;

import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.momo.common.ServiceTest;
import com.momo.domain.common.dto.EnumResponse;
import com.momo.domain.favorite.dto.FavoriteCategoriesUpdateRequest;
import com.momo.domain.favorite.dto.FavoriteGroupCreateRequest;
import com.momo.domain.favorite.entity.FavoriteGroup;
import com.momo.domain.favorite.repository.FavoriteGroupRepository;
import com.momo.domain.favorite.service.impl.FavoriteServiceImpl;
import com.momo.domain.group.entity.Category;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.user.entity.User;
import com.momo.domain.user.repository.UserRepository;
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
        user = User.builder()
            .id(1L)
            .nickname("닉네임")
            .build();
    }

    @Test
    void 관심_모임으로_등록한다() {
        FavoriteGroupCreateRequest request = FavoriteGroupCreateRequest.builder().groupId(1L).build();
        User user = User.builder().id(1L).build();
        Group group = Group.builder().id(1L).build();
        FavoriteGroup favoriteGroup = FavoriteGroup.builder().id(1L).build();

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
        FavoriteCategoriesUpdateRequest categoryRequest = new FavoriteCategoriesUpdateRequest(
            List.of(Category.HOBBY, Category.LIFE)
        );

        given(userRepository.findById(any())).willReturn(of(user));

        favoriteService.updateFavoriteCategories(user, categoryRequest);

        Assertions.assertAll(
            () -> assertThat(user.getFavoriteCategories().getFavoriteCategories().size()).isEqualTo(2),
            () -> assertThat(user.getFavoriteCategories().getFavoriteCategories().get(0).getCategory())
                .isEqualTo(categoryRequest.getFavoriteCategories().get(0)),
            () -> assertThat(user.getFavoriteCategories().getFavoriteCategories().get(1).getCategory())
                .isEqualTo(categoryRequest.getFavoriteCategories().get(1))
        );
    }
}
