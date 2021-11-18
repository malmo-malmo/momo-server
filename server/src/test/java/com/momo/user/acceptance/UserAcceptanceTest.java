package com.momo.user.acceptance;

import static com.momo.fixture.UserFixture.USER1;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.acceptance.step.AcceptanceStep;
import com.momo.common.dto.EnumResponse;
import com.momo.group.controller.dto.CategoryRequest;
import com.momo.user.acceptance.step.UserAcceptanceStep;
import com.momo.user.controller.dto.UserUpdateRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("유저 통합/인수 테스트")
public class UserAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("유저 정보를 수정한다.")
    public void update_success() {
        UserUpdateRequest request = UserUpdateRequest.builder()
            .nickname("모모")
            .university("한국대학교")
            .location("GURO_GU")
            .build();
        String token = getAccessToken(USER1);
        ExtractableResponse<Response> res = UserAcceptanceStep.requestToUpdate(token, request);
        AcceptanceStep.assertThatStatusIsOk(res);
    }

    @Test
    @DisplayName("유저 정보를 수정할 때 잘못된 ENUM 값을 보내면 실패한다.")
    public void update_fail() {
        UserUpdateRequest request = UserUpdateRequest.builder()
            .university("한국대학교")
            .location("ABC")
            .build();
        String token = getAccessToken(USER1);
        ExtractableResponse<Response> res = UserAcceptanceStep.requestToUpdate(token, request);
        AcceptanceStep.assertThatStatusIsBadRequest(res);
    }

    @Test
    @DisplayName("유저 정보를 수정할 때 입력값이 공백 또는 널이면 실패한다.")
    public void update_blank_fail() {
        UserUpdateRequest request = UserUpdateRequest.builder()
            .nickname(" ")
            .build();
        String token = getAccessToken(USER1);
        ExtractableResponse<Response> res = UserAcceptanceStep.requestToUpdate(token, request);
        AcceptanceStep.assertThatStatusIsBadRequest(res);
    }

    @Test
    @DisplayName("유저가 관심 카테고리를 수정한다.")
    public void updateGroupCategories_success() {
        CategoryRequest req = new CategoryRequest(
            List.of("HEALTH", "EMPLOYMENT", "HOBBY"));
        String token = getAccessToken(USER1);
        ExtractableResponse<Response> res = UserAcceptanceStep.requestToUpdateCategory(token,
            req);
        AcceptanceStep.assertThatStatusIsOk(res);
    }

    @Test
    @DisplayName("유저 관심 카테고리를 수정할 때 잘못된 ENUM 값을 보내면 실패한다.")
    public void updateGroupCategories_fail() {
        CategoryRequest req = new CategoryRequest(List.of("HEALTH", "EMPLOYMENT", "HOBB"));
        String token = getAccessToken(USER1);
        ExtractableResponse<Response> res = UserAcceptanceStep.requestToUpdateCategory(token,
            req);
        AcceptanceStep.assertThatStatusIsBadRequest(res);
    }

    @Test
    @DisplayName("저장 가능한 지역들을 조회한다.")
    public void findLocations_success() {
        String token = getAccessToken(USER1);
        ExtractableResponse<Response> response = UserAcceptanceStep.requestToLocations(token);
        AcceptanceStep.assertThatStatusIsOk(response);
        UserAcceptanceStep.assertThatFindLocations(getObjects(response, EnumResponse.class));
    }
}
