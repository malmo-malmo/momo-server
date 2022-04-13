package com.momo.management.acceptance;

import static com.momo.GroupFixture.getGroupCreateRequest;
import static com.momo.PostFixture.getPostCreateRequest;
import static com.momo.UserFixture.getUser;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsOk;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToCreateGroup;
import static com.momo.group.acceptance.step.ParticipantAcceptanceStep.requestToApplyParticipant;
import static com.momo.group.domain.category.Category.LIFE;
import static com.momo.management.acceptance.step.ManagementAcceptanceStep.assertThatFindMyGroups;
import static com.momo.management.acceptance.step.ManagementAcceptanceStep.assertThatFindMyGroupsSummary;
import static com.momo.management.acceptance.step.ManagementAcceptanceStep.assertThatFindMyPosts;
import static com.momo.management.acceptance.step.ManagementAcceptanceStep.assertThatFindParticipationGroups;
import static com.momo.management.acceptance.step.ManagementAcceptanceStep.assertThatFindParticipationGroupsSummary;
import static com.momo.management.acceptance.step.ManagementAcceptanceStep.requestToFindMyGroups;
import static com.momo.management.acceptance.step.ManagementAcceptanceStep.requestToFindMyGroupsSummary;
import static com.momo.management.acceptance.step.ManagementAcceptanceStep.requestToFindMyPosts;
import static com.momo.management.acceptance.step.ManagementAcceptanceStep.requestToFindParticipationGroupCount;
import static com.momo.management.acceptance.step.ManagementAcceptanceStep.requestToFindParticipationGroups;
import static com.momo.management.acceptance.step.ManagementAcceptanceStep.requestToFindParticipationGroupsSummary;
import static com.momo.post.acceptance.step.PostAcceptanceStep.requestToCreatePost;
import static com.momo.post.entity.PostType.NORMAL;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.group.application.dto.request.GroupCreateRequest;
import com.momo.management.dto.MyGroupCardResponse;
import com.momo.management.dto.MyGroupSummaryResponse;
import com.momo.management.dto.MyPostCardResponse;
import com.momo.management.dto.ParticipationGroupCardResponse;
import com.momo.management.dto.ParticipationGroupCountResponse;
import com.momo.management.dto.ParticipationGroupSummaryResponse;
import com.momo.post.dto.PostCreateRequest;
import com.momo.user.domain.User;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("관리 통합/인수 테스트")
public class ManagementAcceptanceTest extends AcceptanceTest {

    private User user;

    @Override
    @BeforeEach
    protected void setUp() {
        super.setUp();
        user = getUser();
    }


    @Test
    void 참여한_모임_수를_조회한다() {
        String token = getAccessToken(user);
        requestToCreateGroup(token, getGroupCreateRequest(LIFE, user.getUniversity()));
        requestToCreateGroup(token, getGroupCreateRequest(LIFE, user.getUniversity()));

        ExtractableResponse<Response> response = requestToFindParticipationGroupCount(token);
        Long expected = getObject(response, ParticipationGroupCountResponse.class).getCount();

        assertThatStatusIsOk(response);
        assertThat(expected).isEqualTo(2);
    }

    @Test
    void 참여한_모임_목록을_조회한다() {
        String token = getAccessToken(user);
        GroupCreateRequest groupCreateRequest = getGroupCreateRequest(LIFE, user.getUniversity());
        requestToCreateGroup(token, groupCreateRequest);

        ExtractableResponse<Response> response = requestToFindParticipationGroups(token);
        List<ParticipationGroupCardResponse> expected = getObjects(response, ParticipationGroupCardResponse.class);

        assertThatStatusIsOk(response);
        assertThatFindParticipationGroups(expected, groupCreateRequest);
    }

    @Test
    void 그_외_참여한_모임_목록을_조회한다() {
        String token1 = getAccessToken(user);
        String token2 = getAccessToken(getUser());
        GroupCreateRequest groupCreateRequest = getGroupCreateRequest(LIFE, user.getUniversity());
        Long groupId = extractId(requestToCreateGroup(token2, groupCreateRequest));
        requestToApplyParticipant(token1, groupId);

        ExtractableResponse<Response> response = requestToFindParticipationGroupsSummary(token1);

        assertThatStatusIsOk(response);
        assertThatFindParticipationGroupsSummary(
            getObjects(response, ParticipationGroupSummaryResponse.class), groupCreateRequest
        );
    }

    @Test
    void 내_모임_목록을_조회한다() {
        String token = getAccessToken(user);
        GroupCreateRequest groupCreateRequest = getGroupCreateRequest(LIFE, user.getUniversity());
        requestToCreateGroup(token, groupCreateRequest);

        ExtractableResponse<Response> response = requestToFindMyGroups(token);

        assertThatStatusIsOk(response);
        assertThatFindMyGroups(getObjects(response, MyGroupCardResponse.class), groupCreateRequest);
    }

    @Test
    void 내_모임_요약_정보_목록을_조회한다() {
        String token = getAccessToken(user);
        GroupCreateRequest groupCreateRequest = getGroupCreateRequest(LIFE, user.getUniversity());
        requestToCreateGroup(token, groupCreateRequest);

        ExtractableResponse<Response> response = requestToFindMyGroupsSummary(token);

        assertThatStatusIsOk(response);
        assertThatFindMyGroupsSummary(getObjects(response, MyGroupSummaryResponse.class), groupCreateRequest);
    }

    @Test
    void 내_게시글_목록을_조회한다() {
        String token = getAccessToken(user);
        Long groupId = extractId(requestToCreateGroup(token, getGroupCreateRequest(LIFE, user.getUniversity())));
        PostCreateRequest postCreateRequest = getPostCreateRequest(groupId, NORMAL);
        requestToCreatePost(token, postCreateRequest);

        ExtractableResponse<Response> response = requestToFindMyPosts(token);

        assertThatStatusIsOk(response);
        assertThatFindMyPosts(getObjects(response, MyPostCardResponse.class), postCreateRequest);
    }
}
