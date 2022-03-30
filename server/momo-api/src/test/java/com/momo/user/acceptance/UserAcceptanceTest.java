package com.momo.user.acceptance;

import static com.momo.UserFixture.getUser;
import static com.momo.common.FixtureComponents.IMAGE;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsBadRequest;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsOk;
import static com.momo.district.entity.City.SEOUL;
import static com.momo.user.acceptance.step.UserAcceptanceStep.assertThatFindMyInformation;
import static com.momo.user.acceptance.step.UserAcceptanceStep.assertThatUpdateMyInformation;
import static com.momo.user.acceptance.step.UserAcceptanceStep.assertThatUpdateMyInformationWithImage;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToFindMyInformation;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToUpdateMyInformation;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToUpdateMyInformationWithImage;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.user.dto.response.UserResponse;
import com.momo.user.dto.request.UserUpdateRequest;
import com.momo.user.dto.response.UserUpdateResponse;
import com.momo.user.domain.model.User;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    void 내_정보를_조회한다() {
        String token = getAccessToken(user);

        ExtractableResponse<Response> response = requestToFindMyInformation(token);
        UserResponse userResponse = getObject(response, UserResponse.class);

        assertThatStatusIsOk(response);
        assertThatFindMyInformation(userResponse, user);
    }

    @Test
    void 내_정보를_수정한다_이미지_포함() {
        String token = getAccessToken(user);
        UserUpdateRequest actual = UserUpdateRequest.builder()
            .nickname("모모")
            .university("한국대학교")
            .city(SEOUL)
            .district("강동구")
            .image(IMAGE)
            .build();

        ExtractableResponse<Response> response = requestToUpdateMyInformationWithImage(token, actual);
        UserUpdateResponse expected = getObject(response, UserUpdateResponse.class);

        assertThatStatusIsOk(response);
        assertThatUpdateMyInformationWithImage(expected, actual);
    }

    @Test
    void 내_정보를_수정한다_이미지_미포함() {
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
    void 내_정보를_수정할_때_입력값이_공백_또는_널이면_실패한다_이미지_미포함() {
        String token = getAccessToken(user);
        UserUpdateRequest userUpdateRequest = UserUpdateRequest.builder()
            .nickname(" ")
            .build();

        ExtractableResponse<Response> response = requestToUpdateMyInformation(token, userUpdateRequest);

        assertThatStatusIsBadRequest(response);
    }
}
