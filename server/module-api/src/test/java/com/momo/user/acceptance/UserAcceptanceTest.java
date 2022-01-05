package com.momo.user.acceptance;

import static com.momo.fixture.UserFixture.getUser1;
import static com.momo.user.acceptance.step.UserAcceptanceStep.assertThatFindMyInformation;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToFindMyInformation;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToUpdate;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.acceptance.step.AcceptanceStep;
import com.momo.domain.district.entity.City;
import com.momo.domain.group.entity.Category;
import com.momo.domain.user.dto.FavoriteCategoriesUpdateRequest;
import com.momo.domain.user.dto.UserResponse;
import com.momo.domain.user.dto.UserUpdateRequest;
import com.momo.user.acceptance.step.UserAcceptanceStep;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("유저 통합/인수 테스트")
public class UserAcceptanceTest extends AcceptanceTest {

    @Test
    public void 내_정보를_조회한다() {
        String token = getAccessToken(getUser1());
        ExtractableResponse<Response> response = requestToFindMyInformation(token);
        UserResponse userResponse = getObject(response, UserResponse.class);
        AcceptanceStep.assertThatStatusIsOk(response);
        assertThatFindMyInformation(userResponse, getUser1());
    }

    @Test
    public void 내_정보를_수정한다() {
        UserUpdateRequest userUpdateRequest = UserUpdateRequest.builder()
            .nickname("모모")
            .university("한국대학교")
            .city(City.SEOUL)
            .district("강동구")
            .build();
        String token = getAccessToken(getUser1());
        ExtractableResponse<Response> response = requestToUpdate(token, userUpdateRequest);
        AcceptanceStep.assertThatStatusIsOk(response);
    }

    @Test
    public void 내_정보를_수정할_때_입력값이_공백_또는_널이면_실패한다() {
        UserUpdateRequest userUpdateRequest = UserUpdateRequest.builder()
            .nickname(" ")
            .build();
        String token = getAccessToken(getUser1());
        ExtractableResponse<Response> response = requestToUpdate(token, userUpdateRequest);
        AcceptanceStep.assertThatStatusIsBadRequest(response);
    }

    @Test
    public void 관심_카테고리를_수정한다() {
        FavoriteCategoriesUpdateRequest request = new FavoriteCategoriesUpdateRequest(
            List.of(Category.HEALTH, Category.EMPLOYMENT, Category.HOBBY));
        String token = getAccessToken(getUser1());
        ExtractableResponse<Response> response = UserAcceptanceStep.requestToUpdateFavoriteCategories(token, request);
        AcceptanceStep.assertThatStatusIsOk(response);
    }

    @Test
    @Disabled
    public void 관심_카테고리를_수정할_때_잘못된_ENUM_값을_보내면_실패한다() {
        FavoriteCategoriesUpdateRequest request = new FavoriteCategoriesUpdateRequest(
            List.of(Category.HEALTH, Category.EMPLOYMENT, Category.HOBBY)
        );
        String token = getAccessToken(getUser1());
        ExtractableResponse<Response> response = UserAcceptanceStep.requestToUpdateFavoriteCategories(token, request);
        AcceptanceStep.assertThatStatusIsBadRequest(response);
    }
}
