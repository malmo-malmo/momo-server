package com.momo.domain.user;

import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.momo.ServiceTest;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.domain.model.Category;
import com.momo.domain.group.dto.CategoryRequest;
import com.momo.domain.user.domain.model.User;
import com.momo.domain.user.domain.repository.UserRepository;
import com.momo.domain.user.dto.UserUpdateRequest;
import com.momo.domain.user.service.UserService;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@DisplayName("유저 서비스 테스트")
public class UserServiceTest extends ServiceTest {

    @Mock
    private UserRepository userRepository;

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
    void 변경할_닉네임이_다른_경우_유저_정보_업데이트_테스트() {
        UserUpdateRequest userUpdateRequest = UserUpdateRequest.builder()
            .nickname("변경할 닉네임")
            .university("변경할 대학교")
            .city("도시")
            .district("행정구역")
            .build();

        given(userRepository.findById(any())).willReturn(of(user));
        given(userRepository.existsByNickname(any())).willReturn(false);

        userService.update(user, userUpdateRequest);

        verify(userRepository).findById(any());
        verify(userRepository).existsByNickname(any());
        Assertions.assertAll(
            () -> assertThat(user.getNickname()).isEqualTo(userUpdateRequest.getNickname()),
            () -> assertThat(user.getUniversity()).isEqualTo(userUpdateRequest.getUniversity()),
            () -> assertThat(user.getCity()).isEqualTo(userUpdateRequest.getCity()),
            () -> assertThat(user.getDistrict()).isEqualTo(userUpdateRequest.getDistrict())
        );
    }

    @Test
    void 변경할_닉네임이_같은_경우_유저_정보_업데이트_테스트() {
        UserUpdateRequest userUpdateRequest = UserUpdateRequest.builder()
            .nickname("닉네임")
            .university("변경할 대학교")
            .city("도시")
            .district("행정구역")
            .build();

        given(userRepository.findById(any())).willReturn(of(user));

        userService.update(user, userUpdateRequest);

        verify(userRepository).findById(any());
        verify(userRepository, never()).existsByNickname(any());
        Assertions.assertAll(
            () -> assertThat(user.getNickname()).isEqualTo(userUpdateRequest.getNickname()),
            () -> assertThat(user.getUniversity()).isEqualTo(userUpdateRequest.getUniversity()),
            () -> assertThat(user.getCity()).isEqualTo(userUpdateRequest.getCity()),
            () -> assertThat(user.getDistrict()).isEqualTo(userUpdateRequest.getDistrict())
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
    void 유저_관심_카테고리_정보_업데이트_테스트() {
        CategoryRequest categoryRequest = new CategoryRequest(List.of("HOBBY", "LIFE"));

        given(userRepository.findById(any())).willReturn(of(user));

        userService.updateCategories(user, categoryRequest);

        Assertions.assertAll(
            () -> assertThat(user.getFavoriteCategories().getFavoriteCategories().size()).isEqualTo(2),
            () -> assertThat(user.getFavoriteCategories().getFavoriteCategories().get(0).getCategory())
                .isEqualTo(Category.of(categoryRequest.getCategories().get(0))),
            () -> assertThat(user.getFavoriteCategories().getFavoriteCategories().get(1).getCategory())
                .isEqualTo(Category.of(categoryRequest.getCategories().get(1)))
        );
    }
}
