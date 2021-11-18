package com.momo.group.acceptance;

import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST1;
import static com.momo.fixture.UserFixture.USER1;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.acceptance.step.AcceptanceStep;
import com.momo.common.dto.EnumResponse;
import com.momo.group.acceptance.step.GroupAcceptanceStep;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("모임 통합/인수 테스트")
public class GroupAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("모임을 생성한다.")
    public void createGroup_success() {
        String token = getAccessToken(USER1);
        ExtractableResponse<Response> res = GroupAcceptanceStep.requestToCreate(token, GROUP_CREATE_REQUEST1);
        AcceptanceStep.assertThatStatusIsCreated(res);
    }

    @Test
    @DisplayName("모임 카테고리를 조회한다.")
    public void findCategories_success() {
        String token = getAccessToken(USER1);
        ExtractableResponse<Response> response = GroupAcceptanceStep.requestToFindCategories(token);
        AcceptanceStep.assertThatStatusIsOk(response);
        GroupAcceptanceStep.assertThatFindCategory(getObjects(response, EnumResponse.class));
    }
}
