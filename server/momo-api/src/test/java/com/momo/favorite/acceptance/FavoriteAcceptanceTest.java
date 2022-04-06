package com.momo.favorite.acceptance;

import static com.momo.FavoriteFixture.getFavoriteGroupCreateRequest;
import static com.momo.GroupFixture.getGroupCreateRequest;
import static com.momo.UserFixture.getUser;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsCreated;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsNoContent;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsOk;
import static com.momo.group.domain.category.Category.EMPLOYMENT;
import static com.momo.group.domain.category.Category.HEALTH;
import static com.momo.group.domain.category.Category.LIFE;
import static com.momo.favorite.acceptance.step.FavoriteAcceptanceStep.assertThatFindFavoriteCategories;
import static com.momo.favorite.acceptance.step.FavoriteAcceptanceStep.assertThatFindFavoriteGroups;
import static com.momo.favorite.acceptance.step.FavoriteAcceptanceStep.requestToCreateFavoriteGroup;
import static com.momo.favorite.acceptance.step.FavoriteAcceptanceStep.requestToDeleteFavoriteGroup;
import static com.momo.favorite.acceptance.step.FavoriteAcceptanceStep.requestToFindFavoriteCategories;
import static com.momo.favorite.acceptance.step.FavoriteAcceptanceStep.requestToFindFavoriteGroupCount;
import static com.momo.favorite.acceptance.step.FavoriteAcceptanceStep.requestToFindFavoriteGroups;
import static com.momo.favorite.acceptance.step.FavoriteAcceptanceStep.requestToUpdateFavoriteCategories;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToCreateGroup;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.dto.EnumResponse;
import com.momo.user.application.dto.request.FavoriteCategoriesUpdateRequest;
import com.momo.group.application.dto.response.FavoriteGroupCardResponse;
import com.momo.group.application.dto.response.FavoriteGroupCountResponse;
import com.momo.group.application.dto.request.FavoriteGroupCreateRequest;
import com.momo.group.application.dto.request.GroupCreateRequest;
import com.momo.user.domain.User;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("관심 통합/인수 테스트")
public class FavoriteAcceptanceTest extends AcceptanceTest {

    private User user;

    @Override
    @BeforeEach
    protected void setUp() {
        super.setUp();
        user = getUser();
    }

    @Test
    void 관심_모임을_등록한다() {
        String token = getAccessToken(user);
        Long groupId = extractId(requestToCreateGroup(token, getGroupCreateRequest(LIFE, true)));
        FavoriteGroupCreateRequest request = getFavoriteGroupCreateRequest(groupId);

        ExtractableResponse<Response> response = requestToCreateFavoriteGroup(token, request);

        assertThatStatusIsCreated(response);
    }

    @Test
    void 관심_모임으로_등록한_모임_수를_조회한다() {
        String token = getAccessToken(user);
        Long groupId = extractId(requestToCreateGroup(token, getGroupCreateRequest(LIFE, true)));
        requestToCreateFavoriteGroup(token, FavoriteGroupCreateRequest.builder().groupId(groupId).build());

        ExtractableResponse<Response> response = requestToFindFavoriteGroupCount(token);

        assertThatStatusIsOk(response);
        assertThat(getObject(response, FavoriteGroupCountResponse.class).getCount()).isEqualTo(1);
    }

    @Test
    void 관심_모임_목록을_조회한다() {
        String token = getAccessToken(user);
        GroupCreateRequest groupCreateRequest = getGroupCreateRequest(LIFE, true);
        Long groupId = extractId(requestToCreateGroup(token, groupCreateRequest));
        requestToCreateFavoriteGroup(token, FavoriteGroupCreateRequest.builder().groupId(groupId).build());

        ExtractableResponse<Response> response = requestToFindFavoriteGroups(token);

        assertThatStatusIsOk(response);
        assertThatFindFavoriteGroups(getObjects(response, FavoriteGroupCardResponse.class), groupCreateRequest);
    }

    @Test
    void 관심_카테고리_목록을_조회한다() {
        FavoriteCategoriesUpdateRequest request = new FavoriteCategoriesUpdateRequest(List.of(HEALTH, EMPLOYMENT));
        String token = getAccessToken(user);
        requestToUpdateFavoriteCategories(token, request);

        ExtractableResponse<Response> response = requestToFindFavoriteCategories(token);

        assertThatStatusIsOk(response);
        assertThatFindFavoriteCategories(getObjects(response, EnumResponse.class), request);
    }

    @Test
    void 관심_카테고리를_수정한다() {
        FavoriteCategoriesUpdateRequest request = new FavoriteCategoriesUpdateRequest(List.of(HEALTH, EMPLOYMENT));
        String token = getAccessToken(user);

        ExtractableResponse<Response> response = requestToUpdateFavoriteCategories(token, request);

        assertThatStatusIsOk(response);
    }

    @Test
    void 관심_모임을_삭제한다() {
        String token = getAccessToken(user);
        Long groupId = extractId(requestToCreateGroup(token, getGroupCreateRequest(LIFE, true)));
        requestToCreateFavoriteGroup(token, getFavoriteGroupCreateRequest(groupId));

        ExtractableResponse<Response> response = requestToDeleteFavoriteGroup(token, groupId);

        assertThatStatusIsNoContent(response);
        assertThat(getObjects(requestToFindFavoriteGroups(token), FavoriteGroupCardResponse.class).size()).isEqualTo(0);
    }
}
