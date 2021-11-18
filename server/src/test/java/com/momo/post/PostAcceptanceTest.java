package com.momo.post;

import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST1;
import static com.momo.fixture.PostFixture.POST_CREATE_REQUEST1;
import static com.momo.fixture.UserFixture.USER1;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.acceptance.step.AcceptanceStep;
import com.momo.group.acceptance.step.GroupAcceptanceStep;
import com.momo.post.step.PostAcceptanceStep;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("게시글 통합/인수 테스트")
public class PostAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("모임에 게시물을 등록한다.")
    public void createGroupPost_success() {
        String token = getAccessToken(USER1);
        Long groupId = extractId(GroupAcceptanceStep.requestToCreate(token, GROUP_CREATE_REQUEST1));
        ExtractableResponse<Response> res = PostAcceptanceStep.requestToCreate(token, POST_CREATE_REQUEST1, groupId);
        AcceptanceStep.assertThatStatusIsCreated(res);
    }
}
