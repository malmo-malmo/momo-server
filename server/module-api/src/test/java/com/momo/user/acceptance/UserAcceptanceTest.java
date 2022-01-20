package com.momo.user.acceptance;

import static com.momo.CommonFileUploadSupport.uploadTestFile;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsBadRequest;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsCreated;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsNoContent;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsOk;
import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST1;
import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST2;
import static com.momo.fixture.UserFixture.getUser1;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToCreateGroup;
import static com.momo.user.acceptance.step.UserAcceptanceStep.assertThatFindFavoriteCategories;
import static com.momo.user.acceptance.step.UserAcceptanceStep.assertThatFindFavoriteGroups;
import static com.momo.user.acceptance.step.UserAcceptanceStep.assertThatFindMyInformation;
import static com.momo.user.acceptance.step.UserAcceptanceStep.assertThatFindParticipatingGroups;
import static com.momo.user.acceptance.step.UserAcceptanceStep.assertThatUpdateMyInformation;
import static com.momo.user.acceptance.step.UserAcceptanceStep.assertThatUpdateMyInformationWithImage;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToCreateFavoriteGroup;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToDeleteFavoriteGroup;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToFindFavoriteCategories;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToFindFavoriteGroupCount;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToFindFavoriteGroups;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToFindMyInformation;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToFindParticipatingGroupCount;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToFindParticipatingGroups;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToUpdateFavoriteCategories;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToUpdateMyInformation;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToUpdateMyInformationWithImage;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.domain.common.dto.EnumResponse;
import com.momo.domain.district.entity.City;
import com.momo.domain.group.entity.Category;
import com.momo.domain.user.dto.FavoriteCategoriesUpdateRequest;
import com.momo.domain.user.dto.FavoriteGroupCardResponse;
import com.momo.domain.user.dto.FavoriteGroupCountResponse;
import com.momo.domain.user.dto.FavoriteGroupCreateRequest;
import com.momo.domain.user.dto.ParticipatingGroupCardResponse;
import com.momo.domain.user.dto.ParticipatingGroupCountResponse;
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
    void 참여한_모임_수를_조회한다() {
        String token = getAccessToken(getUser1());
        requestToCreateGroup(token, GROUP_CREATE_REQUEST1);
        requestToCreateGroup(token, GROUP_CREATE_REQUEST2);
        ExtractableResponse<Response> response = requestToFindParticipatingGroupCount(token);
        Long participatingGroupCount = getObject(response, ParticipatingGroupCountResponse.class).getCount();
        assertThatStatusIsOk(response);
        assertThat(participatingGroupCount).isEqualTo(2);
    }

    @Test
    void 참여한_모임_목록을_조회한다() {
        String token = getAccessToken(getUser1());
        requestToCreateGroup(token, GROUP_CREATE_REQUEST1);
        ExtractableResponse<Response> response = requestToFindParticipatingGroups(token);
        List<ParticipatingGroupCardResponse> cardResponses = getObjects(response, ParticipatingGroupCardResponse.class);
        assertThatStatusIsOk(response);
        assertThatFindParticipatingGroups(cardResponses, GROUP_CREATE_REQUEST1);
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
        UserResponse userResponse = getObject(requestToFindMyInformation(token), UserResponse.class);
        assertThatStatusIsOk(response);
        assertThatUpdateMyInformationWithImage(userResponse, userUpdateRequest);
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
        UserResponse userResponse = getObject(requestToFindMyInformation(token), UserResponse.class);
        assertThatStatusIsOk(response);
        assertThatUpdateMyInformation(userResponse, userUpdateRequest);
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
