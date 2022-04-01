package com.momo.user.acceptance;

import static com.momo.UserFixture.getUser;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsOk;
import static com.momo.district.entity.City.SEOUL;
import static com.momo.user.acceptance.step.UserAcceptanceStep.assertThatFindMyInformation;
import static com.momo.user.acceptance.step.UserAcceptanceStep.assertThatUpdateImage;
import static com.momo.user.acceptance.step.UserAcceptanceStep.assertThatUpdateImageWithImage;
import static com.momo.user.acceptance.step.UserAcceptanceStep.assertThatUpdateMyInformation;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToFindMyInformation;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToUpdateImage;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToUpdateImageWithImage;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToUpdateMyInformation;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.user.domain.model.User;
import com.momo.user.dto.request.UserUpdateRequest;
import com.momo.user.dto.response.UserImageUpdateResponse;
import com.momo.user.dto.response.UserResponse;
import com.momo.user.dto.response.UserUpdateResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@DisplayName("유저 통합/인수 테스트")
public class UserAcceptanceTest extends AcceptanceTest {

    private User user;

    @Override
    @BeforeEach
    protected void setUp() {
        super.setUp();
        user = getUser();
    }

    @Test
    @DisplayName("내 정보를 조회한다")
    void findMyInformation_success() {
        String token = getAccessToken(user);

        ExtractableResponse<Response> response = requestToFindMyInformation(token);
        UserResponse userResponse = getObject(response, UserResponse.class);

        assertThatStatusIsOk(response);
        assertThatFindMyInformation(userResponse, user);
    }

    @Test
    @DisplayName("내 정보를 수정한다")
    void updateMyInformation_success() {
        String token = getAccessToken(user);
        UserUpdateRequest userUpdateRequest = UserUpdateRequest.builder()
            .nickname("모모")
            .university("한국대학교")
            .city(SEOUL)
            .district("강동구")
            .build();

        ExtractableResponse<Response> response = requestToUpdateMyInformation(token, userUpdateRequest);
        UserUpdateResponse userUpdateResponse = getObject(response, UserUpdateResponse.class);

        assertThatStatusIsOk(response);
        assertThatUpdateMyInformation(userUpdateResponse, userUpdateRequest);
    }

    @Test
    @DisplayName("내 프로필 이미지를 수정한다 - 이미지 포함")
    void updateImage_success1() throws IOException {
        String token = getAccessToken(user);
        MultipartFile imageFile = new MockMultipartFile("imageFile", "imageFile".getBytes());

        ExtractableResponse<Response> response = requestToUpdateImageWithImage(token, imageFile);
        UserImageUpdateResponse userImageUpdateResponse = getObject(response, UserImageUpdateResponse.class);

        assertThatStatusIsOk(response);
        assertThatUpdateImageWithImage(userImageUpdateResponse);
    }

    @Test
    @DisplayName("내 프로필 이미지를 수정한다 - 이미지 미포함")
    void updateImage_success2() {
        String token = getAccessToken(user);

        ExtractableResponse<Response> response = requestToUpdateImage(token);
        UserImageUpdateResponse userImageUpdateResponse = getObject(response, UserImageUpdateResponse.class);

        assertThatStatusIsOk(response);
        assertThatUpdateImage(userImageUpdateResponse);
    }
}
