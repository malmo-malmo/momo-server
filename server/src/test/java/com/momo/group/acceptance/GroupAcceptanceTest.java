package com.momo.group.acceptance;

import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST1;
import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST2;
import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST3;
import static com.momo.fixture.UserFixture.USER1;
import static com.momo.fixture.UserFixture.USER2;
import static com.momo.fixture.UserFixture.USER3;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.assertThatFindGroup;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToCreateGroup;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToFindCategories;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToFindGroup;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToFindGroupsByCategories;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToFindGroupsByLocation;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToFindGroupsByUserUniversity;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.acceptance.step.AcceptanceStep;
import com.momo.common.dto.EnumResponse;
import com.momo.group.acceptance.step.GroupAcceptanceStep;
import com.momo.group.controller.dto.GroupCardResponse;
import com.momo.group.controller.dto.GroupResponse;
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
        assertThatFindGroup(GROUP_CREATE_REQUEST1, groupResponse, true, true, USER1.getUniversity());
    }

    @Test
    public void 모임에_참여하지_않은_유저가_모임을_상세_조회한다() {
        Long groupId = extractId(requestToCreateGroup(getAccessToken(USER1), GROUP_CREATE_REQUEST1));
        ExtractableResponse<Response> response = requestToFindGroup(getAccessToken(USER2), groupId);
        GroupResponse groupResponse = getObject(response, GroupResponse.class);
        AcceptanceStep.assertThatStatusIsOk(response);
        assertThatFindGroup(GROUP_CREATE_REQUEST1, groupResponse, false, false, USER1.getUniversity());
    }

    @Test
    public void 로그인한_유저의_학교로_모임을_전체_조회한다() {
        requestToCreateGroup(getAccessToken(USER1), GROUP_CREATE_REQUEST1); //같은 학교 모임
        requestToCreateGroup(getAccessToken(USER2), GROUP_CREATE_REQUEST2); //다른 학교 모임
        requestToCreateGroup(getAccessToken(USER3), GROUP_CREATE_REQUEST3); //다른 학교 모임
        ExtractableResponse<Response> response = requestToFindGroupsByUserUniversity(getAccessToken(USER1));
        List<GroupCardResponse> groupCardResponses = getObjects(response, GroupCardResponse.class);
        AcceptanceStep.assertThatStatusIsOk(response);
        assertThat(groupCardResponses.size()).isEqualTo(1);
    }

    @Test
    public void 로그인한_유저의_지역로_모임을_전체_조회한다() {
        requestToCreateGroup(getAccessToken(USER1), GROUP_CREATE_REQUEST1); //같은 지역 모임
        requestToCreateGroup(getAccessToken(USER2), GROUP_CREATE_REQUEST2); //같은 지역 모임
        requestToCreateGroup(getAccessToken(USER3), GROUP_CREATE_REQUEST3); //다른 지역 모임
        ExtractableResponse<Response> response = requestToFindGroupsByLocation(getAccessToken(USER1));
        List<GroupCardResponse> groupCardResponses = getObjects(response, GroupCardResponse.class);
        AcceptanceStep.assertThatStatusIsOk(response);
        assertThat(groupCardResponses.size()).isEqualTo(2);
    }

    @Test
    public void 로그인한_유저의_관심_카테고리로_모임을_전체_조회한다() {
        requestToCreateGroup(getAccessToken(USER1), GROUP_CREATE_REQUEST1); //관심 카테고리 모임 O
        requestToCreateGroup(getAccessToken(USER2), GROUP_CREATE_REQUEST2); //관심 카테고리 모임 O
        requestToCreateGroup(getAccessToken(USER3), GROUP_CREATE_REQUEST3); //관심 카테고리 모임 O
        ExtractableResponse<Response> response = requestToFindGroupsByCategories(getAccessToken(USER1));
        List<GroupCardResponse> groupCardResponses = getObjects(response, GroupCardResponse.class);
        AcceptanceStep.assertThatStatusIsOk(response);
        assertThat(groupCardResponses.size()).isEqualTo(3);
    }

    @Test
    public void 모임_카테고리를_조회한다() {
        String token = getAccessToken(USER1);
        ExtractableResponse<Response> response = requestToFindCategories(token);
        AcceptanceStep.assertThatStatusIsOk(response);
        GroupAcceptanceStep.assertThatFindCategory(getObjects(response, EnumResponse.class));
    }
}
