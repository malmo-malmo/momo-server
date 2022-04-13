package com.momo.schedule.acceptance;

import static com.momo.GroupFixture.getGroupCreateRequest;
import static com.momo.ScheduleFixture.getScheduleCreateRequest;
import static com.momo.UserFixture.getUser;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatCustomException;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsOk;
import static com.momo.common.exception.ErrorCode.GROUP_MANAGER_AUTHORIZED;
import static com.momo.group.domain.category.Category.LIFE;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToCreateGroup;
import static com.momo.schedule.acceptance.step.ScheduleAcceptanceStep.assertThatFindGroupSchedule;
import static com.momo.schedule.acceptance.step.ScheduleAcceptanceStep.assertThatFindUpcomingSchedule;
import static com.momo.schedule.acceptance.step.ScheduleAcceptanceStep.assertThatFindUserSchedules;
import static com.momo.schedule.acceptance.step.ScheduleAcceptanceStep.requestToCreateSchedule;
import static com.momo.schedule.acceptance.step.ScheduleAcceptanceStep.requestToFindGroupSchedules;
import static com.momo.schedule.acceptance.step.ScheduleAcceptanceStep.requestToFindUpcomingSchedule;
import static com.momo.schedule.acceptance.step.ScheduleAcceptanceStep.requestToFindUserSchedules;
import static java.time.LocalDateTime.of;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.acceptance.step.AcceptanceStep;
import com.momo.schedule.dto.GroupScheduleResponses;
import com.momo.schedule.dto.ScheduleCreateRequest;
import com.momo.schedule.dto.UpcomingScheduleResponse;
import com.momo.schedule.dto.UserScheduleResponse;
import com.momo.user.domain.User;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("일정 통합/인수 테스트")
public class ScheduleAcceptanceTest extends AcceptanceTest {

    @Test
    void 모임_관리자가_모임에_일정을_등록한다() {
        String token = getAccessToken(getUser());
        Long groupId = extractId(requestToCreateGroup(token, getGroupCreateRequest(LIFE, null)));

        ExtractableResponse<Response> response =
            requestToCreateSchedule(token, getScheduleCreateRequest(groupId, of(2022, 1, 1, 1, 0)));

        AcceptanceStep.assertThatStatusIsCreated(response);
    }

    @Test
    void 모임_관리자가_아니면_일정_등록을_실패한다() {
        String token = getAccessToken(getUser());
        String invalidToken = getAccessToken(getUser());
        Long groupId = extractId(requestToCreateGroup(token, getGroupCreateRequest(LIFE, null)));

        ExtractableResponse<Response> response =
            requestToCreateSchedule(invalidToken, getScheduleCreateRequest(groupId, of(2022, 1, 1, 1, 0)));

        assertThatCustomException(response, GROUP_MANAGER_AUTHORIZED);
    }

    @Test
    void 모임_참여자가_모임의_일정_목록을_조회한다() {
        User author = getUser();
        String token = getAccessToken(author);
        Long groupId = extractId(requestToCreateGroup(token, getGroupCreateRequest(LIFE, null)));
        ScheduleCreateRequest actual = getScheduleCreateRequest(groupId, of(2022, 1, 1, 1, 0));
        requestToCreateSchedule(token, actual);

        ExtractableResponse<Response> response = requestToFindGroupSchedules(token, groupId);
        GroupScheduleResponses expected = getObject(response, GroupScheduleResponses.class);

        assertThatStatusIsOk(response);
        assertThatFindGroupSchedule(actual, expected, author, false, false);
    }

    @Test
    void 로그인한_유저가_캘린더에서_선택한_월의_모든_일정을_조회한다() {
        String token = getAccessToken(getUser());
        Long groupId = extractId(requestToCreateGroup(token, getGroupCreateRequest(LIFE, null)));
        requestToCreateSchedule(token, getScheduleCreateRequest(groupId, of(2022, 1, 1, 1, 0)));
        requestToCreateSchedule(token, getScheduleCreateRequest(groupId, of(2022, 2, 1, 1, 0)));

        ExtractableResponse<Response> response = requestToFindUserSchedules(token, "2022-01-01", "2022-01-31");
        List<UserScheduleResponse> expected = getObjects(response, UserScheduleResponse.class);

        assertThatStatusIsOk(response);
        assertThatFindUserSchedules(expected);
    }

    @Test
    void 다가오는_일정을_조회한다() {
        String token = getAccessToken(getUser());
        Long groupId = extractId(requestToCreateGroup(token, getGroupCreateRequest(LIFE, null)));
        ScheduleCreateRequest request = getScheduleCreateRequest(groupId, LocalDateTime.now().plusHours(1));

        requestToCreateSchedule(token, request);
        requestToCreateSchedule(token, getScheduleCreateRequest(groupId, LocalDateTime.now().plusHours(2)));

        ExtractableResponse<Response> response = requestToFindUpcomingSchedule(token, groupId);

        assertThatStatusIsOk(response);
        assertThatFindUpcomingSchedule(getObject(response, UpcomingScheduleResponse.class), request);
    }
}
