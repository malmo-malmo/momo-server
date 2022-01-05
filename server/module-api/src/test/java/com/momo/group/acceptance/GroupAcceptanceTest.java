package com.momo.group.acceptance;

import static com.momo.common.acceptance.step.AcceptanceStep.assertThatCustomException;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsCreated;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsOk;
import static com.momo.domain.common.exception.ErrorCode.GROUP_MANAGER_AUTHORIZED;
import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST1;
import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST2;
import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST3;
import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST4;
import static com.momo.fixture.UserFixture.getUser1;
import static com.momo.fixture.UserFixture.getUser2;
import static com.momo.fixture.UserFixture.getUser3;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.assertThatEndGroup;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.assertThatFindCategory;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.assertThatFindGroup;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToCreateGroup;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToEndGroup;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToFindCategories;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToFindGroup;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToFindGroupsByCategories;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToFindGroupsByDistrict;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToFindGroupsBySearchCondition;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToFindGroupsByUserUniversity;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToUpdateManager;
import static com.momo.group.acceptance.step.ParticipantAcceptanceStep.requestToApplyParticipant;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToFindMyInformation;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.domain.common.dto.EnumResponse;
import com.momo.domain.district.entity.City;
import com.momo.domain.group.dto.GroupCardResponse;
import com.momo.domain.group.dto.GroupResponse;
import com.momo.domain.group.dto.GroupSearchConditionRequest;
import com.momo.domain.group.entity.Category;
import com.momo.domain.user.dto.UserResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("모임 통합/인수 테스트")
public class GroupAcceptanceTest extends AcceptanceTest {

    @Test
    void 모임을_생성한다() {
        String token = getAccessToken(getUser1());
        ExtractableResponse<Response> response = requestToCreateGroup(token, GROUP_CREATE_REQUEST1);
        assertThatStatusIsCreated(response);
    }

    @Test
    void 관리자가_모임을_상세_조회한다() {
        String token = getAccessToken(getUser1());
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        ExtractableResponse<Response> response = requestToFindGroup(token, groupId);
        GroupResponse groupResponse = getObject(response, GroupResponse.class);
        assertThatStatusIsOk(response);
        assertThatFindGroup(GROUP_CREATE_REQUEST1, groupResponse, true, getUser1().getUniversity());
    }

    @Test
    void 모임에_참여하지_않은_유저가_모임을_상세_조회한다() {
        Long groupId = extractId(requestToCreateGroup(getAccessToken(getUser1()), GROUP_CREATE_REQUEST1));
        ExtractableResponse<Response> response = requestToFindGroup(getAccessToken(getUser2()), groupId);
        GroupResponse groupResponse = getObject(response, GroupResponse.class);
        assertThatStatusIsOk(response);
        assertThatFindGroup(GROUP_CREATE_REQUEST1, groupResponse, false, getUser1().getUniversity());
    }

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
    }

    @Test
    void 로그인한_유저의_학교로_모임_목록을_조회한다() {
        requestToCreateGroup(getAccessToken(getUser1()), GROUP_CREATE_REQUEST1); //같은 학교 모임
        requestToCreateGroup(getAccessToken(getUser2()), GROUP_CREATE_REQUEST2); //다른 학교 모임
        requestToCreateGroup(getAccessToken(getUser3()), GROUP_CREATE_REQUEST3); //다른 학교 모임
        ExtractableResponse<Response> response = requestToFindGroupsByUserUniversity(getAccessToken(getUser1()));
        List<GroupCardResponse> groupCardResponses = getObjects(response, GroupCardResponse.class);
        assertThatStatusIsOk(response);
        assertThat(groupCardResponses.size()).isEqualTo(1);
    }

    @Test
    void 로그인한_유저의_지역으로_모임_목록을_조회한다() {
        requestToCreateGroup(getAccessToken(getUser1()), GROUP_CREATE_REQUEST1); //같은 지역 모임
        requestToCreateGroup(getAccessToken(getUser2()), GROUP_CREATE_REQUEST2); //같은 지역 모임
        requestToCreateGroup(getAccessToken(getUser3()), GROUP_CREATE_REQUEST3); //다른 지역 모임
        ExtractableResponse<Response> response = requestToFindGroupsByDistrict(getAccessToken(getUser1()));
        List<GroupCardResponse> groupCardResponses = getObjects(response, GroupCardResponse.class);
        assertThatStatusIsOk(response);
        assertThat(groupCardResponses.size()).isEqualTo(2);
    }

    @Test
    void 로그인한_유저의_관심_카테고리로_모임_목록을_조회한다() {
        requestToCreateGroup(getAccessToken(getUser1()), GROUP_CREATE_REQUEST1); //관심 카테고리 모임 O
        requestToCreateGroup(getAccessToken(getUser2()), GROUP_CREATE_REQUEST2); //관심 카테고리 모임 O
        requestToCreateGroup(getAccessToken(getUser3()), GROUP_CREATE_REQUEST3); //관심 카테고리 모임 O
        ExtractableResponse<Response> response = requestToFindGroupsByCategories(getAccessToken(getUser1()));
        List<GroupCardResponse> groupCardResponses = getObjects(response, GroupCardResponse.class);
        assertThatStatusIsOk(response);
        assertThat(groupCardResponses.size()).isEqualTo(3);
    }

    @Test
    void 모임_카테고리_목록을_조회한다() {
        String token = getAccessToken(getUser1());
        ExtractableResponse<Response> response = requestToFindCategories(token);
        assertThatStatusIsOk(response);
        assertThatFindCategory(getObjects(response, EnumResponse.class));
    }

    @Test
    void 모임장이_권한을_양도한다() {
        String managerToken = getAccessToken(getUser1());
        String participantToken = getAccessToken(getUser2());
        Long participantId = getObject(requestToFindMyInformation(participantToken), UserResponse.class).getId();
        Long groupId = extractId(requestToCreateGroup(managerToken, GROUP_CREATE_REQUEST1));
        requestToApplyParticipant(participantToken, groupId);
        ExtractableResponse<Response> response = requestToUpdateManager(managerToken, groupId, participantId);
        assertThatStatusIsOk(response);
    }

    @Test
    void 모임장이_아닌_유저가_권한을_양도하면_실패한다() {
        String managerToken = getAccessToken(getUser1());
        String participantToken = getAccessToken(getUser2());
        Long groupId = extractId(requestToCreateGroup(managerToken, GROUP_CREATE_REQUEST1));
        requestToApplyParticipant(participantToken, groupId);
        ExtractableResponse<Response> response = requestToUpdateManager(participantToken, groupId, 1L);
        assertThatCustomException(response, GROUP_MANAGER_AUTHORIZED);
    }

    @Test
    void 모임장이_모임을_종료한다() {
        String token = getAccessToken(getUser1());
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        ExtractableResponse<Response> response = requestToEndGroup(token, groupId);
        assertThatStatusIsOk(response);
        GroupResponse groupResponse = getObject(requestToFindGroup(token, groupId), GroupResponse.class);
        assertThatEndGroup(groupResponse);
    }

    @Test
    void 모임장이_아닌_유저가_모임을_종료하면_실패한다() {
        String managerToken = getAccessToken(getUser1());
        String participantToken = getAccessToken(getUser2());
        Long groupId = extractId(requestToCreateGroup(managerToken, GROUP_CREATE_REQUEST1));
        ExtractableResponse<Response> response = requestToEndGroup(participantToken, groupId);
        assertThatCustomException(response, GROUP_MANAGER_AUTHORIZED);
    }
}
