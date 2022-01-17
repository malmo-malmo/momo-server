package com.momo.user.acceptance;

import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsBadRequest;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsCreated;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsNoContent;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsOk;
import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST1;
import static com.momo.fixture.UserFixture.getUser1;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToCreateGroup;
import static com.momo.user.acceptance.step.UserAcceptanceStep.assertThatFindFavoriteGroups;
import static com.momo.user.acceptance.step.UserAcceptanceStep.assertThatFindMyInformation;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToCreateFavoriteGroup;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToDeleteFavoriteGroup;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToFindFavoriteGroupCount;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToFindFavoriteGroups;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToFindMyInformation;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToUpdate;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToUpdateFavoriteCategories;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.domain.district.entity.City;
import com.momo.domain.group.entity.Category;
import com.momo.domain.user.dto.FavoriteCategoriesUpdateRequest;
import com.momo.domain.user.dto.FavoriteGroupCardResponse;
import com.momo.domain.user.dto.FavoriteGroupCountResponse;
import com.momo.domain.user.dto.FavoriteGroupCreateRequest;
import com.momo.domain.user.dto.UserResponse;
import com.momo.domain.user.dto.UserUpdateRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("유저 통합/인수 테스트")
public class UserAcceptanceTest extends AcceptanceTest {

    @Test
    void 관심_모임을_등록한다() {
        String token = getAccessToken(getUser1());
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        FavoriteGroupCreateRequest request = FavoriteGroupCreateRequest.builder().groupId(groupId).build();
        ExtractableResponse<Response> response = requestToCreateFavoriteGroup(token, request);
        assertThatStatusIsCreated(response);
    }

    @Test
    void 내_정보를_조회한다() {
        String token = getAccessToken(getUser1());
        ExtractableResponse<Response> response = requestToFindMyInformation(token);
        UserResponse userResponse = getObject(response, UserResponse.class);
        assertThatStatusIsOk(response);
        assertThatFindMyInformation(userResponse, getUser1());
    }

    @Test
    void 관심_모임으로_등록한_모임_수를_조회한다() {
        String token = getAccessToken(getUser1());
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        requestToCreateFavoriteGroup(token, FavoriteGroupCreateRequest.builder().groupId(groupId).build());
        ExtractableResponse<Response> response = requestToFindFavoriteGroupCount(token);
        FavoriteGroupCountResponse favoriteGroupCountResponse = getObject(response, FavoriteGroupCountResponse.class);
        assertThatStatusIsOk(response);
        assertThat(favoriteGroupCountResponse.getCount()).isEqualTo(1);
    }

    @Test
    void 관심_모임_목록을_조회한다() {
        String token = getAccessToken(getUser1());
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        requestToCreateFavoriteGroup(token, FavoriteGroupCreateRequest.builder().groupId(groupId).build());
        ExtractableResponse<Response> response = requestToFindFavoriteGroups(token);
        List<FavoriteGroupCardResponse> favoriteGroupResponses = getObjects(response, FavoriteGroupCardResponse.class);
        assertThatStatusIsOk(response);
        assertThatFindFavoriteGroups(favoriteGroupResponses, GROUP_CREATE_REQUEST1);
    }

    @Test
    void 내_정보를_수정한다() {
        UserUpdateRequest userUpdateRequest = UserUpdateRequest.builder()
            .nickname("모모")
            .university("한국대학교")
            .city(City.SEOUL)
            .district("강동구")
            .build();
        String token = getAccessToken(getUser1());
        ExtractableResponse<Response> response = requestToUpdate(token, userUpdateRequest);
        assertThatStatusIsOk(response);
    }

    @Test
    void 내_정보를_수정할_때_입력값이_공백_또는_널이면_실패한다() {
        UserUpdateRequest userUpdateRequest = UserUpdateRequest.builder()
            .nickname(" ")
            .build();
        String token = getAccessToken(getUser1());
        ExtractableResponse<Response> response = requestToUpdate(token, userUpdateRequest);
        assertThatStatusIsBadRequest(response);
    }

    @Test
    void 관심_카테고리를_수정한다() {
        FavoriteCategoriesUpdateRequest request = new FavoriteCategoriesUpdateRequest(
            List.of(Category.HEALTH, Category.EMPLOYMENT, Category.HOBBY)
        );
        String token = getAccessToken(getUser1());
        ExtractableResponse<Response> response = requestToUpdateFavoriteCategories(token, request);
        assertThatStatusIsOk(response);
    }

    @Test
    @Disabled
    void 관심_카테고리를_수정할_때_잘못된_ENUM_값을_보내면_실패한다() {
        FavoriteCategoriesUpdateRequest request = new FavoriteCategoriesUpdateRequest(
            List.of(Category.HEALTH, Category.EMPLOYMENT, Category.HOBBY)
        );
        String token = getAccessToken(getUser1());
        ExtractableResponse<Response> response = requestToUpdateFavoriteCategories(token, request);
        assertThatStatusIsBadRequest(response);
    }

    @Test
    void 관심_모임을_삭제한다() {
        String token = getAccessToken(getUser1());
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        Long favoriteGroupId = extractId(
            requestToCreateFavoriteGroup(token, FavoriteGroupCreateRequest.builder().groupId(groupId).build())
        );
        ExtractableResponse<Response> response = requestToDeleteFavoriteGroup(token, favoriteGroupId);
        List<FavoriteGroupCardResponse> favoriteGroupResponses = getObjects(
            requestToFindFavoriteGroups(token), FavoriteGroupCardResponse.class
        );
        assertThatStatusIsNoContent(response);
        assertThat(favoriteGroupResponses.size()).isEqualTo(0);
    }
}
