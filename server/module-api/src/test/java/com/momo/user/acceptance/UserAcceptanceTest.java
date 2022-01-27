package com.momo.user.acceptance;

import static com.momo.CommonFileUploadSupport.uploadTestFile;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsBadRequest;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsOk;
import static com.momo.fixture.UserFixture.getUser1;
import static com.momo.user.acceptance.step.UserAcceptanceStep.assertThatFindMyInformation;
import static com.momo.user.acceptance.step.UserAcceptanceStep.assertThatUpdateMyInformation;
import static com.momo.user.acceptance.step.UserAcceptanceStep.assertThatUpdateMyInformationWithImage;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToFindMyInformation;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToUpdateMyInformation;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToUpdateMyInformationWithImage;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.domain.district.entity.City;
import com.momo.domain.user.dto.UserResponse;
import com.momo.domain.user.dto.UserUpdateRequest;
import com.momo.domain.user.dto.UserUpdateResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("유저 통합/인수 테스트")
public class UserAcceptanceTest extends AcceptanceTest {

    @Test
    void 내_정보를_조회한다() {
        String token = getAccessToken(getUser1());
        ExtractableResponse<Response> response = requestToFindMyInformation(token);
        UserResponse userResponse = getObject(response, UserResponse.class);
        assertThatStatusIsOk(response);
        assertThatFindMyInformation(userResponse, getUser1());
    }

    @Test
    void 내_정보를_수정한다_이미지_포함() {
        UserUpdateRequest userUpdateRequest = UserUpdateRequest.builder()
            .nickname("모모")
            .university("한국대학교")
            .city(City.SEOUL)
            .district("강동구")
            .image(uploadTestFile)
            .build();
        String token = getAccessToken(getUser1());
        ExtractableResponse<Response> response = requestToUpdateMyInformationWithImage(token, userUpdateRequest);
        UserUpdateResponse userUpdateResponse = getObject(response, UserUpdateResponse.class);
        assertThatStatusIsOk(response);
        assertThatUpdateMyInformationWithImage(userUpdateResponse, userUpdateRequest);
    }

    @Test
    void 내_정보를_수정한다_이미지_미포함() {
        UserUpdateRequest userUpdateRequest = UserUpdateRequest.builder()
            .nickname("모모")
            .university("한국대학교")
            .city(City.SEOUL)
            .district("강동구")
            .build();
        String token = getAccessToken(getUser1());
        ExtractableResponse<Response> response = requestToUpdateMyInformation(token, userUpdateRequest);
        UserUpdateResponse userUpdateResponse = getObject(response, UserUpdateResponse.class);
        assertThatStatusIsOk(response);
        assertThatUpdateMyInformation(userUpdateResponse, userUpdateRequest);
    }

    @Test
    void 내_정보를_수정할_때_입력값이_공백_또는_널이면_실패한다_이미지_미포함() {
        UserUpdateRequest userUpdateRequest = UserUpdateRequest.builder()
            .nickname(" ")
            .build();
        String token = getAccessToken(getUser1());
        ExtractableResponse<Response> response = requestToUpdateMyInformation(token, userUpdateRequest);
        assertThatStatusIsBadRequest(response);
    }
}
