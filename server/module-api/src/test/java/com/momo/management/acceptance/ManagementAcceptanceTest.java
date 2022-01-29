package com.momo.management.acceptance;

import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsOk;
import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST1;
import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST2;
import static com.momo.fixture.UserFixture.getUser1;
import static com.momo.fixture.UserFixture.getUser2;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToCreateGroup;
import static com.momo.group.acceptance.step.ParticipantAcceptanceStep.requestToApplyParticipant;
import static com.momo.management.acceptance.step.ManagementAcceptanceStep.assertThatFindMyGroupsSummary;
import static com.momo.management.acceptance.step.ManagementAcceptanceStep.assertThatFindMyPosts;
import static com.momo.management.acceptance.step.ManagementAcceptanceStep.assertThatFindParticipationGroups;
import static com.momo.management.acceptance.step.ManagementAcceptanceStep.assertThatFindParticipationGroupsSummary;
import static com.momo.management.acceptance.step.ManagementAcceptanceStep.requestToFindMyGroupsSummary;
import static com.momo.management.acceptance.step.ManagementAcceptanceStep.requestToFindMyPosts;
import static com.momo.management.acceptance.step.ManagementAcceptanceStep.requestToFindParticipationGroupCount;
import static com.momo.management.acceptance.step.ManagementAcceptanceStep.requestToFindParticipationGroups;
import static com.momo.management.acceptance.step.ManagementAcceptanceStep.requestToFindParticipationGroupsSummary;
import static com.momo.post.acceptance.step.PostAcceptanceStep.requestToCreatePost;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.domain.group.dto.GroupResponse;
import com.momo.domain.management.dto.MyGroupSummaryResponse;
import com.momo.domain.management.dto.MyPostCardResponse;
import com.momo.domain.management.dto.ParticipationGroupCardResponse;
import com.momo.domain.management.dto.ParticipationGroupCountResponse;
import com.momo.domain.management.dto.ParticipationGroupSummaryResponse;
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
        ExtractableResponse<Response> response = requestToFindParticipationGroupCount(token);
        Long participationGroupCount = getObject(response, ParticipationGroupCountResponse.class).getCount();
        assertThatStatusIsOk(response);
        assertThat(participationGroupCount).isEqualTo(2);
    }

    @Test
    void 참여한_모임_목록을_조회한다() {
        String token = getAccessToken(getUser1());
        requestToCreateGroup(token, GROUP_CREATE_REQUEST1);
        ExtractableResponse<Response> response = requestToFindParticipationGroups(token);
        List<ParticipationGroupCardResponse> cardResponses = getObjects(response, ParticipationGroupCardResponse.class);
        assertThatStatusIsOk(response);
        assertThatFindParticipationGroups(cardResponses, GROUP_CREATE_REQUEST1);
    }

    @Test
    void 그_외_참여한_모임_목록을_조회한다() {
        String token1 = getAccessToken(getUser1());
        String token2 = getAccessToken(getUser2());
        requestToCreateGroup(token1, GROUP_CREATE_REQUEST1);
        Long groupId = getObject(requestToCreateGroup(token2, GROUP_CREATE_REQUEST2), GroupResponse.class).getId();
        requestToApplyParticipant(token1, groupId);

        ExtractableResponse<Response> response = requestToFindParticipationGroupsSummary(token1);

        assertThatStatusIsOk(response);
        assertThatFindParticipationGroupsSummary(
            getObjects(response, ParticipationGroupSummaryResponse.class), GROUP_CREATE_REQUEST2
        );
    }

    @Test
    void 내_모임_요약_정보_목록을_조회한다() {
        String token1 = getAccessToken(getUser1());
        String token2 = getAccessToken(getUser2());
        requestToCreateGroup(token1, GROUP_CREATE_REQUEST1);
        Long groupId = getObject(requestToCreateGroup(token2, GROUP_CREATE_REQUEST2), GroupResponse.class).getId();
        requestToApplyParticipant(token1, groupId);

        ExtractableResponse<Response> response = requestToFindMyGroupsSummary(token1);

        assertThatStatusIsOk(response);
        assertThatFindMyGroupsSummary(getObjects(response, MyGroupSummaryResponse.class), GROUP_CREATE_REQUEST1);
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
