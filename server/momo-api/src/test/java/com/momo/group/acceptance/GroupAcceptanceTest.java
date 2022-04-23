package com.momo.group.acceptance;

import static com.momo.GroupFixture.getGroupCreateRequest;
import static com.momo.UserFixture.getUser;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatCustomException;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsCreated;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsOk;
import static com.momo.common.exception.ErrorCode.GROUP_MANAGER_AUTHORIZED;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.assertThatEndGroup;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.assertThatFindCategory;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.assertThatFindGroup;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToCreateGroup;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToEndGroup;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToFindCategories;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToFindGroup;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToFindGroupsByCategories;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToFindGroupsByDistrict;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToFindGroupsByUserUniversity;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToUpdateManager;
import static com.momo.group.acceptance.step.ParticipantAcceptanceStep.requestToApplyParticipant;
import static com.momo.group.domain.category.Category.HOBBY;
import static com.momo.group.domain.category.Category.LIFE;
import static com.momo.group.domain.category.Category.STOCK;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToFindMyInformation;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.dto.EnumResponse;
import com.momo.group.application.dto.request.GroupCreateRequest;
import com.momo.group.application.dto.response.GroupCardResponse;
import com.momo.group.application.dto.response.GroupResponse;
import com.momo.user.application.dto.response.UserResponse;
import com.momo.user.domain.User;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("모임 통합/인수 테스트")
public class GroupAcceptanceTest extends AcceptanceTest {

    private User user;

    @Override
    @BeforeEach
    protected void setUp() {
        super.setUp();
        user = getUser();
    }

    @Test
    void 모임을_생성한다() {
        String token = getAccessToken(user);
        ExtractableResponse<Response> response = requestToCreateGroup(
            token, getGroupCreateRequest(LIFE, user.getUniversity())
        );
        assertThatStatusIsCreated(response);
    }

    @Test
    void 관리자가_모임을_상세_조회한다() {
        String token = getAccessToken(user);
        GroupCreateRequest groupCreateRequest = getGroupCreateRequest(LIFE, user.getUniversity());
        Long groupId = extractId(requestToCreateGroup(token, groupCreateRequest));

        ExtractableResponse<Response> response = requestToFindGroup(token, groupId);
        GroupResponse groupResponse = getObject(response, GroupResponse.class);

        assertThatStatusIsOk(response);
        assertThatFindGroup(groupCreateRequest, groupResponse, true, user.getUniversity());
    }

    @Test
    void 모임에_참여하지_않은_유저가_모임을_상세_조회한다() {
        String token1 = getAccessToken(user);
        String token2 = getAccessToken(getUser());
        GroupCreateRequest groupCreateRequest = getGroupCreateRequest(LIFE, user.getUniversity());
        Long groupId = extractId(requestToCreateGroup(token1, groupCreateRequest));

        ExtractableResponse<Response> response = requestToFindGroup(token2, groupId);
        GroupResponse groupResponse = getObject(response, GroupResponse.class);

        assertThatStatusIsOk(response);
        assertThatFindGroup(groupCreateRequest, groupResponse, false, user.getUniversity());
    }

    /*
    TODO : 통합검색 구현 후 리팩토링
    @Test
    void 유저가_검색_조건으로_모임_목록을_조회한다() {
        requestToCreateGroup(getAccessToken(getUser1()), GROUP_CREATE_REQUEST1); //서울 건강
        requestToCreateGroup(getAccessToken(getUser2()), GROUP_CREATE_REQUEST2); //서울 자기계발
        requestToCreateGroup(getAccessToken(getUser3()), GROUP_CREATE_REQUEST3); //서울 건강
        requestToCreateGroup(getAccessToken(getUser3()), GROUP_CREATE_REQUEST4); //경기 밥약
        GroupSearchConditionRequest request = GroupSearchConditionRequest.builder()
            .cities(List.of(City.SEOUL, City.GYEONGGI))
            .categories(List.of(Category.HEALTH, Category.RICE))
            .page(0)
            .size(10)
            .build();
        ExtractableResponse<Response> response = requestToFindGroupsBySearchCondition(getAccessToken(getUser1()),
            request);
        List<GroupCardResponse> groupCardResponses = getObjects(response, GroupCardResponse.class);
        assertThatStatusIsOk(response);
        assertThat(groupCardResponses.size()).isEqualTo(3);
    }

    @Test
    void 유저가_검색_조건없이_모임_목록을_조회한다() {
        requestToCreateGroup(getAccessToken(getUser1()), GROUP_CREATE_REQUEST1); //서울 건강
        requestToCreateGroup(getAccessToken(getUser2()), GROUP_CREATE_REQUEST2); //서울 자기계발
        GroupSearchConditionRequest request = GroupSearchConditionRequest.builder()
            .page(0)
            .size(10)
            .build();
        ExtractableResponse<Response> response = requestToFindGroupsBySearchCondition(getAccessToken(getUser1()),
            request);
        List<GroupCardResponse> groupCardResponses = getObjects(response, GroupCardResponse.class);
        assertThatStatusIsOk(response);
        assertThat(groupCardResponses.size()).isEqualTo(2);
    }*/

    @Test
    void 로그인한_유저의_학교로_모임_목록을_조회한다() {
        User user1 = getUser("강동구", "대학교1", List.of(LIFE));
        User user2 = getUser("강동구", "대학교1", List.of(LIFE));
        User user3 = getUser("강동구", "대학교2", List.of(LIFE));
        requestToCreateGroup(getAccessToken(user2), getGroupCreateRequest(LIFE, user2.getUniversity()));
        requestToCreateGroup(getAccessToken(user3), getGroupCreateRequest(LIFE, user3.getUniversity()));

        ExtractableResponse<Response> response = requestToFindGroupsByUserUniversity(getAccessToken(user1));

        assertThatStatusIsOk(response);
        assertThat(getObjects(response, GroupCardResponse.class).size()).isEqualTo(1);
    }

    @Test
    void 로그인한_유저의_지역으로_모임_목록을_조회한다() {
        String token = getAccessToken(getUser("강동구", "대학교1", List.of(LIFE)));
        requestToCreateGroup(getAccessToken(getUser()), getGroupCreateRequest(LIFE, user.getUniversity(), "강동구"));
        requestToCreateGroup(getAccessToken(getUser()), getGroupCreateRequest(LIFE, user.getUniversity(), "강남구"));

        ExtractableResponse<Response> response = requestToFindGroupsByDistrict(token);

        assertThatStatusIsOk(response);
        assertThat(getObjects(response, GroupCardResponse.class).size()).isEqualTo(1);
    }

    @Test
    void 로그인한_유저의_관심_카테고리로_모임_목록을_조회한다() {
        user = getUser("강동구", "대학교1", List.of(LIFE, STOCK));
        requestToCreateGroup(getAccessToken(getUser()), getGroupCreateRequest(LIFE, user.getUniversity()));
        requestToCreateGroup(getAccessToken(getUser()), getGroupCreateRequest(STOCK, user.getUniversity()));
        requestToCreateGroup(getAccessToken(getUser()), getGroupCreateRequest(HOBBY, user.getUniversity()));

        ExtractableResponse<Response> response = requestToFindGroupsByCategories(getAccessToken(user));

        assertThatStatusIsOk(response);
        assertThat(getObjects(response, GroupCardResponse.class).size()).isEqualTo(2);
    }

    @Test
    void 모임_카테고리_목록을_조회한다() {
        String token = getAccessToken(user);

        ExtractableResponse<Response> response = requestToFindCategories(token);

        assertThatStatusIsOk(response);
        assertThatFindCategory(getObjects(response, EnumResponse.class));
    }

    @Test
    void 모임장이_권한을_양도한다() {
        String managerToken = getAccessToken(user);
        String participantToken = getAccessToken(getUser());
        Long participantId = getObject(requestToFindMyInformation(participantToken), UserResponse.class).getId();
        Long groupId = extractId(requestToCreateGroup(managerToken, getGroupCreateRequest(LIFE, user.getUniversity())));
        requestToApplyParticipant(participantToken, groupId);

        ExtractableResponse<Response> response = requestToUpdateManager(managerToken, groupId, participantId);

        assertThatStatusIsOk(response);
    }

    @Test
    void 모임장이_아닌_유저가_권한을_양도하면_실패한다() {
        String managerToken = getAccessToken(user);
        String participantToken = getAccessToken(getUser());
        Long groupId = extractId(requestToCreateGroup(managerToken, getGroupCreateRequest(LIFE, user.getUniversity())));
        requestToApplyParticipant(participantToken, groupId);

        ExtractableResponse<Response> response = requestToUpdateManager(participantToken, groupId, 1L);

        assertThatCustomException(response, GROUP_MANAGER_AUTHORIZED);
    }

    @Test
    void 모임장이_모임을_종료한다() {
        String token = getAccessToken(user);
        Long groupId = extractId(requestToCreateGroup(token, getGroupCreateRequest(LIFE, user.getUniversity())));

        ExtractableResponse<Response> response = requestToEndGroup(token, groupId);
        GroupResponse groupResponse = getObject(requestToFindGroup(token, groupId), GroupResponse.class);

        assertThatStatusIsOk(response);
        assertThatEndGroup(groupResponse);
    }

    @Test
    void 모임장이_아닌_유저가_모임을_종료하면_실패한다() {
        String managerToken = getAccessToken(user);
        String participantToken = getAccessToken(getUser());
        Long groupId = extractId(requestToCreateGroup(managerToken, getGroupCreateRequest(LIFE, user.getUniversity())));

        ExtractableResponse<Response> response = requestToEndGroup(participantToken, groupId);

        assertThatCustomException(response, GROUP_MANAGER_AUTHORIZED);
    }
}
