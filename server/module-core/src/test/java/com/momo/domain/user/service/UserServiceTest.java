package com.momo.domain.user.service;

import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.momo.common.ServiceTest;
import com.momo.domain.aws.service.S3UploadService;
import com.momo.domain.common.dto.EnumResponse;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.district.entity.City;
import com.momo.domain.group.entity.Category;
import com.momo.domain.user.dto.FavoriteCategoriesUpdateRequest;
import com.momo.domain.user.dto.UserUpdateRequest;
import com.momo.domain.user.entity.User;
import com.momo.domain.user.repository.UserRepository;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;

@DisplayName("유저 서비스 테스트")
public class UserServiceTest extends ServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private S3UploadService s3UploadService;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
            .id(1L)
            .nickname("닉네임")
            .build();
    }

    @Test
    void 변경할_닉네임이_다른_경우_유저_정보를_수정한다_이미지_NULL() {
        UserUpdateRequest userUpdateRequest = UserUpdateRequest.builder()
            .nickname("변경할 닉네임")
            .university("변경할 대학교")
            .city(City.SEOUL)
            .district("행정구역")
            .build();

        given(userRepository.findById(any())).willReturn(of(user));
        given(userRepository.existsByNickname(any())).willReturn(false);
        given(s3UploadService.upload(any(), any())).willReturn(null);

        userService.update(user, userUpdateRequest);

        verify(userRepository).findById(any());
        verify(userRepository).existsByNickname(any());
        verify(s3UploadService).upload(any(), any());
        Assertions.assertAll(
            () -> assertThat(user.getNickname()).isEqualTo(userUpdateRequest.getNickname()),
            () -> assertThat(user.getUniversity()).isEqualTo(userUpdateRequest.getUniversity()),
            () -> assertThat(user.getCity()).isEqualTo(userUpdateRequest.getCity()),
            () -> assertThat(user.getDistrict()).isEqualTo(userUpdateRequest.getDistrict()),
            () -> assertThat(user.getImageUrl()).isNull()
        );
    }

    @Test
    void 변경할_닉네임이_같은_경우_유저_정보를_수정한다_이미지_NOT_NULL() {
        String imageUrl = "imageUrl";
        UserUpdateRequest userUpdateRequest = UserUpdateRequest.builder()
            .nickname(user.getNickname())
            .university("변경할 대학교")
            .city(City.SEOUL)
            .district("행정구역")
            .image(new MockMultipartFile("image", "content".getBytes(StandardCharsets.UTF_8)))
            .build();

        given(userRepository.findById(any())).willReturn(of(user));
        given(s3UploadService.upload(any(), any())).willReturn(imageUrl);

        userService.update(user, userUpdateRequest);

        verify(userRepository).findById(any());
        verify(userRepository, never()).existsByNickname(any());
        verify(s3UploadService).upload(any(), any());
        Assertions.assertAll(
            () -> assertThat(user.getNickname()).isEqualTo(userUpdateRequest.getNickname()),
            () -> assertThat(user.getUniversity()).isEqualTo(userUpdateRequest.getUniversity()),
            () -> assertThat(user.getCity()).isEqualTo(userUpdateRequest.getCity()),
            () -> assertThat(user.getDistrict()).isEqualTo(userUpdateRequest.getDistrict()),
            () -> assertThat(user.getImageUrl()).isEqualTo(imageUrl)
        );
    }

    @Test
    void 중복된_닉네임인_경우_유저_정보_업데이트_테스트를_실패한다() {
        UserUpdateRequest userUpdateRequest = UserUpdateRequest.builder().nickname("중복된 닉네임").build();

        given(userRepository.findById(any())).willReturn(of(user));
        given(userRepository.existsByNickname(any())).willReturn(true);

        assertThatThrownBy(() -> userService.update(user, userUpdateRequest))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.DUPLICATED_NICKNAME.getMessage());
    }

    @Test
    void 유저_관심_카테고리_정보_조회_테스트() {
        List<Category> expected = List.of(Category.HEALTH, Category.EMPLOYMENT);
        user.updateFavoriteCategories(expected);

        List<EnumResponse> actual = userService.findFavoriteCategoriesByUser(user);

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

        userService.updateFavoriteCategories(user, categoryRequest);

        Assertions.assertAll(
            () -> assertThat(user.getFavoriteCategories().getFavoriteCategories().size()).isEqualTo(2),
            () -> assertThat(user.getFavoriteCategories().getFavoriteCategories().get(0).getCategory())
                .isEqualTo(categoryRequest.getFavoriteCategories().get(0)),
            () -> assertThat(user.getFavoriteCategories().getFavoriteCategories().get(1).getCategory())
                .isEqualTo(categoryRequest.getFavoriteCategories().get(1))
        );
    }
}
