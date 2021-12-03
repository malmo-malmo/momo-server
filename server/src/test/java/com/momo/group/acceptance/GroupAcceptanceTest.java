package com.momo.group.acceptance;

import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST1;
import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST2;
import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST3;
import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST4;
import static com.momo.fixture.UserFixture.USER1;
import static com.momo.fixture.UserFixture.USER2;
import static com.momo.fixture.UserFixture.USER3;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.assertThatFindGroup;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToCreateGroup;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToFindCategories;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToFindGroup;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToFindGroupsByCategories;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToFindGroupsByDistrict;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToFindGroupsBySearchCondition;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToFindGroupsByUserUniversity;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToHandOverAuthority;
import static com.momo.group.acceptance.step.ParticipantAcceptanceStep.requestToApplyParticipant;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToFindMyInformation;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.acceptance.step.AcceptanceStep;
import com.momo.common.dto.EnumResponse;
import com.momo.group.acceptance.step.GroupAcceptanceStep;
import com.momo.group.controller.dto.GroupCardResponse;
import com.momo.group.controller.dto.GroupResponse;
import com.momo.group.controller.dto.GroupSearchConditionRequest;
import com.momo.user.controller.dto.UserResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("모임 통합/인수 테스트")
public class GroupAcceptanceTest extends AcceptanceTest {

    @Test
    public void 모임을_생성한다() {
        String token = getAccessToken(USER1);
        ExtractableResponse<Response> response = requestToCreateGroup(token, GROUP_CREATE_REQUEST1);
        AcceptanceStep.assertThatStatusIsCreated(response);
    }

    @Test
    public void 관리자가_모임을_상세_조회한다() {
        String token = getAccessToken(USER1);
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        ExtractableResponse<Response> response = requestToFindGroup(token, groupId);
        GroupResponse groupResponse = getObject(response, GroupResponse.class);
        AcceptanceStep.assertThatStatusIsOk(response);
        assertThatFindGroup(GROUP_CREATE_REQUEST1, groupResponse, true, USER1.getUniversity());
    }

    @Test
    public void 모임에_참여하지_않은_유저가_모임을_상세_조회한다() {
        Long groupId = extractId(requestToCreateGroup(getAccessToken(USER1), GROUP_CREATE_REQUEST1));
        ExtractableResponse<Response> response = requestToFindGroup(getAccessToken(USER2), groupId);
        GroupResponse groupResponse = getObject(response, GroupResponse.class);
        AcceptanceStep.assertThatStatusIsOk(response);
        assertThatFindGroup(GROUP_CREATE_REQUEST1, groupResponse, false, USER1.getUniversity());
    }

    @Test
    public void 유저가_검색_조건으로_모임_목록을_조회한다() {
        requestToCreateGroup(getAccessToken(USER1), GROUP_CREATE_REQUEST1); //서울 건강
        requestToCreateGroup(getAccessToken(USER2), GROUP_CREATE_REQUEST2); //서울 자기계발
        requestToCreateGroup(getAccessToken(USER3), GROUP_CREATE_REQUEST3); //서울 건강
        requestToCreateGroup(getAccessToken(USER3), GROUP_CREATE_REQUEST4); //경기 밥약
        GroupSearchConditionRequest request = GroupSearchConditionRequest.builder()
            .cities(List.of("서울", "경기"))
            .categories(List.of("HEALTH", "RICE"))
            .page(0)
            .size(10)
            .build();
        ExtractableResponse<Response> response = requestToFindGroupsBySearchCondition(getAccessToken(USER1), request);
        List<GroupCardResponse> groupCardResponses = getObjects(response, GroupCardResponse.class);
        AcceptanceStep.assertThatStatusIsOk(response);
        assertThat(groupCardResponses.size()).isEqualTo(3);
    }

    @Test
    public void 유저가_검색_조건없이_모임_목록을_조회한다() {
        requestToCreateGroup(getAccessToken(USER1), GROUP_CREATE_REQUEST1); //서울 건강
        requestToCreateGroup(getAccessToken(USER2), GROUP_CREATE_REQUEST2); //서울 자기계발
        GroupSearchConditionRequest request = GroupSearchConditionRequest.builder()
            .page(0)
            .size(10)
            .build();
        ExtractableResponse<Response> response = requestToFindGroupsBySearchCondition(getAccessToken(USER1), request);
        List<GroupCardResponse> groupCardResponses = getObjects(response, GroupCardResponse.class);
        AcceptanceStep.assertThatStatusIsOk(response);
        assertThat(groupCardResponses.size()).isEqualTo(2);
    }

    @Test
    public void 로그인한_유저의_학교로_모임_목록을_조회한다() {
        requestToCreateGroup(getAccessToken(USER1), GROUP_CREATE_REQUEST1); //같은 학교 모임
        requestToCreateGroup(getAccessToken(USER2), GROUP_CREATE_REQUEST2); //다른 학교 모임
        requestToCreateGroup(getAccessToken(USER3), GROUP_CREATE_REQUEST3); //다른 학교 모임
        ExtractableResponse<Response> response = requestToFindGroupsByUserUniversity(getAccessToken(USER1));
        List<GroupCardResponse> groupCardResponses = getObjects(response, GroupCardResponse.class);
        AcceptanceStep.assertThatStatusIsOk(response);
        assertThat(groupCardResponses.size()).isEqualTo(1);
    }

    @Test
    public void 로그인한_유저의_지역으로_모임_목록을_조회한다() {
        requestToCreateGroup(getAccessToken(USER1), GROUP_CREATE_REQUEST1); //같은 지역 모임
        requestToCreateGroup(getAccessToken(USER2), GROUP_CREATE_REQUEST2); //같은 지역 모임
        requestToCreateGroup(getAccessToken(USER3), GROUP_CREATE_REQUEST3); //다른 지역 모임
        ExtractableResponse<Response> response = requestToFindGroupsByDistrict(getAccessToken(USER1));
        List<GroupCardResponse> groupCardResponses = getObjects(response, GroupCardResponse.class);
        AcceptanceStep.assertThatStatusIsOk(response);
        assertThat(groupCardResponses.size()).isEqualTo(2);
    }

    @Test
    public void 로그인한_유저의_관심_카테고리로_모임_목록을_조회한다() {
        requestToCreateGroup(getAccessToken(USER1), GROUP_CREATE_REQUEST1); //관심 카테고리 모임 O
        requestToCreateGroup(getAccessToken(USER2), GROUP_CREATE_REQUEST2); //관심 카테고리 모임 O
        requestToCreateGroup(getAccessToken(USER3), GROUP_CREATE_REQUEST3); //관심 카테고리 모임 O
        ExtractableResponse<Response> response = requestToFindGroupsByCategories(getAccessToken(USER1));
        List<GroupCardResponse> groupCardResponses = getObjects(response, GroupCardResponse.class);
        AcceptanceStep.assertThatStatusIsOk(response);
        assertThat(groupCardResponses.size()).isEqualTo(3);
    }

    @Test
    public void 모임_카테고리_목록을_조회한다() {
        String token = getAccessToken(USER1);
        ExtractableResponse<Response> response = requestToFindCategories(token);
        AcceptanceStep.assertThatStatusIsOk(response);
        GroupAcceptanceStep.assertThatFindCategory(getObjects(response, EnumResponse.class));
    }

    @Test
    public void 모임장이_권한을_양도한다() {
        String managerToken = getAccessToken(USER1);
        String participantToken = getAccessToken(USER2);
        Long participantId = getObject(requestToFindMyInformation(participantToken), UserResponse.class).getId();
        Long groupId = extractId(requestToCreateGroup(managerToken, GROUP_CREATE_REQUEST1));
        requestToApplyParticipant(participantToken, groupId);
        ExtractableResponse<Response> response = requestToHandOverAuthority(managerToken, groupId, participantId);
        AcceptanceStep.assertThatStatusIsOk(response);
    }

    @Test
    public void 모임장이_아닌_유저가_권한을_양도하면_실패한다() {
        String managerToken = getAccessToken(USER1);
        String participantToken = getAccessToken(USER2);
        Long groupId = extractId(requestToCreateGroup(managerToken, GROUP_CREATE_REQUEST1));
        requestToApplyParticipant(participantToken, groupId);
        ExtractableResponse<Response> response = requestToHandOverAuthority(participantToken, groupId, 1L);
        AcceptanceStep.assertThatErrorIsHandOverUnAuthorized(response);
    }
}
