package com.momo.domain.user.service;

import static com.momo.UserFixture.getUserWithId;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.momo.common.ServiceTest;
import com.momo.aws.service.S3UploadService;
import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.district.entity.City;
import com.momo.user.dto.UserUpdateRequest;
import com.momo.user.dto.UserUpdateResponse;
import com.momo.user.entity.User;
import com.momo.user.repository.UserRepository;
import com.momo.user.service.UserService;
import com.momo.user.service.impl.UserServiceImpl;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;

@DisplayName("유저 서비스 테스트")
public class UserServiceTest extends ServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private S3UploadService s3UploadService;

    private UserService userService;
    private User user;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository, s3UploadService);
        user = getUserWithId();
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

        UserUpdateResponse actual = userService.update(user, userUpdateRequest);

        verify(userRepository).findById(any());
        verify(userRepository).existsByNickname(any());
        verify(s3UploadService).upload(any(), any());
        Assertions.assertAll(
            () -> assertThat(actual.getNickname()).isEqualTo(userUpdateRequest.getNickname()),
            () -> assertThat(actual.getUniversity()).isEqualTo(userUpdateRequest.getUniversity()),
            () -> assertThat(actual.getCity().getCode()).isEqualTo(userUpdateRequest.getCity().getCode()),
            () -> assertThat(actual.getDistrict()).isEqualTo(userUpdateRequest.getDistrict()),
            () -> assertThat(actual.getImageUrl()).isNull()
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

        UserUpdateResponse actual = userService.update(user, userUpdateRequest);

        verify(userRepository).findById(any());
        verify(userRepository, never()).existsByNickname(any());
        verify(s3UploadService).upload(any(), any());
        Assertions.assertAll(
            () -> assertThat(actual.getNickname()).isEqualTo(userUpdateRequest.getNickname()),
            () -> assertThat(actual.getUniversity()).isEqualTo(userUpdateRequest.getUniversity()),
            () -> assertThat(actual.getCity().getCode()).isEqualTo(userUpdateRequest.getCity().getCode()),
            () -> assertThat(actual.getDistrict()).isEqualTo(userUpdateRequest.getDistrict()),
            () -> assertThat(user.getImageUrl()).isEqualTo(imageUrl)
        );
    }

    @Test
    void 중복된_닉네임인_경우_유저_정보_업데이트_테스트를_실패한다() {
        UserUpdateRequest userUpdateRequest = UserUpdateRequest.builder()
            .nickname("중복된 닉네임")
            .build();

        given(userRepository.findById(any())).willReturn(of(user));
        given(userRepository.existsByNickname(any())).willReturn(true);

        assertThatThrownBy(() -> userService.update(user, userUpdateRequest))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.DUPLICATED_NICKNAME.getMessage());
    }
}
