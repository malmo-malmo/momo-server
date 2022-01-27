package com.momo.management.acceptance;

import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsOk;
import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST1;
import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST2;
import static com.momo.fixture.UserFixture.getUser1;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToCreateGroup;
import static com.momo.management.acceptance.step.ManagementAcceptanceStep.assertThatFindMyPosts;
import static com.momo.management.acceptance.step.ManagementAcceptanceStep.assertThatFindParticipatingGroups;
import static com.momo.management.acceptance.step.ManagementAcceptanceStep.requestToFindMyPosts;
import static com.momo.management.acceptance.step.ManagementAcceptanceStep.requestToFindParticipatingGroupCount;
import static com.momo.management.acceptance.step.ManagementAcceptanceStep.requestToFindParticipatingGroups;
import static com.momo.post.acceptance.step.PostAcceptanceStep.requestToCreatePost;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.domain.group.dto.GroupResponse;
import com.momo.domain.management.dto.MyPostCardResponse;
import com.momo.domain.management.dto.ParticipatingGroupCardResponse;
import com.momo.domain.management.dto.ParticipatingGroupCountResponse;
import com.momo.domain.post.dto.PostCreateRequest;
import com.momo.fixture.PostFixture;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("관리 통합/인수 테스트")
public class ManagementAcceptanceTest extends AcceptanceTest {

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
    void 내_게시글_목록을_조회한다() {
        String token = getAccessToken(getUser1());
        GroupResponse groupResponse =
            getObject(requestToCreateGroup(token, GROUP_CREATE_REQUEST1), GroupResponse.class);
        PostCreateRequest postCreateRequest = PostFixture.getPostCreateRequest(groupResponse.getId());
        requestToCreatePost(token, postCreateRequest);

        ExtractableResponse<Response> response = requestToFindMyPosts(token);
        List<MyPostCardResponse> myPostCardResponses = getObjects(response, MyPostCardResponse.class);

        assertThatStatusIsOk(response);
        assertThatFindMyPosts(myPostCardResponses, postCreateRequest, groupResponse.getName());
    }
}
