package com.momo.favorite.acceptance;

import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsBadRequest;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsCreated;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsNoContent;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsOk;
import static com.momo.favorite.acceptance.step.FavoriteAcceptanceStep.assertThatFindFavoriteCategories;
import static com.momo.favorite.acceptance.step.FavoriteAcceptanceStep.assertThatFindFavoriteGroups;
import static com.momo.favorite.acceptance.step.FavoriteAcceptanceStep.requestToCreateFavoriteGroup;
import static com.momo.favorite.acceptance.step.FavoriteAcceptanceStep.requestToDeleteFavoriteGroup;
import static com.momo.favorite.acceptance.step.FavoriteAcceptanceStep.requestToFindFavoriteCategories;
import static com.momo.favorite.acceptance.step.FavoriteAcceptanceStep.requestToFindFavoriteGroupCount;
import static com.momo.favorite.acceptance.step.FavoriteAcceptanceStep.requestToFindFavoriteGroups;
import static com.momo.favorite.acceptance.step.FavoriteAcceptanceStep.requestToUpdateFavoriteCategories;
import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST1;
import static com.momo.fixture.UserFixture.getUser1;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToCreateGroup;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.domain.common.dto.EnumResponse;
import com.momo.domain.favorite.dto.FavoriteCategoriesUpdateRequest;
import com.momo.domain.favorite.dto.FavoriteGroupCardResponse;
import com.momo.domain.favorite.dto.FavoriteGroupCountResponse;
import com.momo.domain.favorite.dto.FavoriteGroupCreateRequest;
import com.momo.domain.group.entity.Category;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("관심 통합/인수 테스트")
public class FavoriteAcceptanceTest extends AcceptanceTest {

    @Test
    void 관심_모임을_등록한다() {
        String token = getAccessToken(getUser1());
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        FavoriteGroupCreateRequest request = FavoriteGroupCreateRequest.builder().groupId(groupId).build();
        ExtractableResponse<Response> response = requestToCreateFavoriteGroup(token, request);
        assertThatStatusIsCreated(response);
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
    void 관심_카테고리_목록을_조회한다() {
        FavoriteCategoriesUpdateRequest request = new FavoriteCategoriesUpdateRequest(
            List.of(Category.HEALTH, Category.EMPLOYMENT)
        );
        String token = getAccessToken(getUser1());
        requestToUpdateFavoriteCategories(token, request);
        ExtractableResponse<Response> response = requestToFindFavoriteCategories(token);
        assertThatStatusIsOk(response);
        assertThatFindFavoriteCategories(getObjects(response, EnumResponse.class), request);
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
        requestToCreateFavoriteGroup(token, FavoriteGroupCreateRequest.builder().groupId(groupId).build());
        ExtractableResponse<Response> response = requestToDeleteFavoriteGroup(token, groupId);
        List<FavoriteGroupCardResponse> favoriteGroupResponses = getObjects(
            requestToFindFavoriteGroups(token), FavoriteGroupCardResponse.class
        );
        assertThatStatusIsNoContent(response);
        assertThat(favoriteGroupResponses.size()).isEqualTo(0);
    }
}
