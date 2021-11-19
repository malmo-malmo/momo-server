package com.momo.group.acceptance;

import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST1;
import static com.momo.fixture.UserFixture.USER1;
import static com.momo.fixture.UserFixture.USER2;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.acceptance.step.AcceptanceStep;
import com.momo.common.dto.EnumResponse;
import com.momo.group.acceptance.step.GroupAcceptanceStep;
import com.momo.group.controller.dto.GroupResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("모임 통합/인수 테스트")
public class GroupAcceptanceTest extends AcceptanceTest {

    @Test
    public void 모임을_생성한다() {
        String token = getAccessToken(USER1);
        ExtractableResponse<Response> res = GroupAcceptanceStep.requestToCreateGroup(token, GROUP_CREATE_REQUEST1);
        AcceptanceStep.assertThatStatusIsCreated(res);
    }

    @Test
    public void 관리자가_모임을_상세_조회한다() {
        String token = getAccessToken(USER1);
        Long groupId = extractId(GroupAcceptanceStep.requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        ExtractableResponse<Response> res = GroupAcceptanceStep.requestToFindGroup(token, groupId);
        GroupResponse groupResponse = getObject(res, GroupResponse.class);
        AcceptanceStep.assertThatStatusIsOk(res);
        GroupAcceptanceStep.assertThatFindGroup(GROUP_CREATE_REQUEST1, groupResponse, true, true);
    }

    @Test
    public void 모임에_참여하지_않은_유저가_모임을_상세_조회한다() {
        Long groupId = extractId(
            GroupAcceptanceStep.requestToCreateGroup(getAccessToken(USER1), GROUP_CREATE_REQUEST1));
        ExtractableResponse<Response> res = GroupAcceptanceStep.requestToFindGroup(getAccessToken(USER2), groupId);
        GroupResponse groupResponse = getObject(res, GroupResponse.class);
        AcceptanceStep.assertThatStatusIsOk(res);
        GroupAcceptanceStep.assertThatFindGroup(GROUP_CREATE_REQUEST1, groupResponse, false, false);
    }

    @Test
    public void 모임_카테고리를_조회한다() {
        String token = getAccessToken(USER1);
        ExtractableResponse<Response> response = GroupAcceptanceStep.requestToFindCategories(token);
        AcceptanceStep.assertThatStatusIsOk(response);
        GroupAcceptanceStep.assertThatFindCategory(getObjects(response, EnumResponse.class));
    }
}
