package com.momo.schedule.acceptance;

import static com.momo.AttendanceFixture.getAttendanceCreateRequest;
import static com.momo.AttendanceFixture.getAttendanceCreateRequests;
import static com.momo.AttendanceFixture.getAttendanceUpdateRequest;
import static com.momo.AttendanceFixture.getAttendanceUpdateRequests;
import static com.momo.GroupFixture.getGroupCreateRequest;
import static com.momo.ScheduleFixture.getScheduleCreateRequest;
import static com.momo.UserFixture.getUser;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsCreated;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsOk;
import static com.momo.group.domain.category.Category.LIFE;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToCreateGroup;
import static com.momo.group.acceptance.step.ParticipantAcceptanceStep.requestToApplyParticipant;
import static com.momo.group.acceptance.step.ParticipantAcceptanceStep.requestToFindParticipants;
import static com.momo.schedule.acceptance.step.AttendanceAcceptanceStep.assertThatFindAttendance;
import static com.momo.schedule.acceptance.step.AttendanceAcceptanceStep.assertThatUpdateAttendance;
import static com.momo.schedule.acceptance.step.AttendanceAcceptanceStep.requestToCreateAttendance;
import static com.momo.schedule.acceptance.step.AttendanceAcceptanceStep.requestToFindAttendances;
import static com.momo.schedule.acceptance.step.AttendanceAcceptanceStep.requestToUpdateAttendance;
import static com.momo.schedule.acceptance.step.ScheduleAcceptanceStep.requestToCreateSchedule;
import static java.time.LocalDateTime.of;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.group.application.dto.ParticipantResponse;
import com.momo.schedule.dto.AttendanceCreateRequests;
import com.momo.schedule.dto.AttendanceResponse;
import com.momo.schedule.dto.AttendanceUpdateRequests;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("출석 통합/인수 테스트")
public class AttendanceAcceptanceTest extends AcceptanceTest {

    @Test
    void 모임_관리자가_일정_출석_체크를_한다() {
        String managerToken = getAccessToken(getUser());
        String userToken = getAccessToken(getUser());
        Long groupId = extractId(requestToCreateGroup(managerToken, getGroupCreateRequest(LIFE, true)));
        Long scheduleId = extractId(
            requestToCreateSchedule(managerToken, getScheduleCreateRequest(groupId, of(2022, 1, 1, 1, 0)))
        );
        requestToApplyParticipant(userToken, groupId);
        List<ParticipantResponse> participantResponses = getObjects(
            requestToFindParticipants(managerToken, groupId), ParticipantResponse.class
        );
        AttendanceCreateRequests requests = getAttendanceCreateRequests(
            scheduleId,
            List.of(
                getAttendanceCreateRequest(participantResponses.get(0).getParticipantId(), true),
                getAttendanceCreateRequest(participantResponses.get(1).getParticipantId(), true)
            )
        );

        ExtractableResponse<Response> response = requestToCreateAttendance(managerToken, requests);

        assertThatStatusIsCreated(response);
    }

    @Test
    void 모임_관리자가_일정_출석체크_목록을_조회한다() {
        String managerToken = getAccessToken(getUser());
        String userToken = getAccessToken(getUser());
        Long groupId = extractId(requestToCreateGroup(managerToken, getGroupCreateRequest(LIFE, true)));
        Long scheduleId = extractId(
            requestToCreateSchedule(managerToken, getScheduleCreateRequest(groupId, of(2022, 1, 1, 1, 0)))
        );
        requestToApplyParticipant(userToken, groupId);
        List<ParticipantResponse> participantResponses = getObjects(
            requestToFindParticipants(managerToken, groupId), ParticipantResponse.class
        );
        AttendanceCreateRequests requests = getAttendanceCreateRequests(
            scheduleId,
            List.of(
                getAttendanceCreateRequest(participantResponses.get(0).getParticipantId(), true),
                getAttendanceCreateRequest(participantResponses.get(1).getParticipantId(), true)
            )
        );
        requestToCreateAttendance(managerToken, requests);

        ExtractableResponse<Response> response = requestToFindAttendances(managerToken, scheduleId);
        List<AttendanceResponse> expected = getObjects(response, AttendanceResponse.class);

        assertThatStatusIsOk(response);
        assertThatFindAttendance(requests.getAttendanceCreateRequests(), expected);
    }

    @Test
    void 모임_관리자가_출석_체크를_수정한다() {
        String managerToken = getAccessToken(getUser());
        String userToken = getAccessToken(getUser());
        Long groupId = extractId(requestToCreateGroup(managerToken, getGroupCreateRequest(LIFE, true)));
        Long scheduleId = extractId(
            requestToCreateSchedule(managerToken, getScheduleCreateRequest(groupId, of(2022, 1, 1, 1, 0)))
        );
        requestToApplyParticipant(userToken, groupId);
        List<ParticipantResponse> participantResponses = getObjects(
            requestToFindParticipants(managerToken, groupId), ParticipantResponse.class
        );
        AttendanceCreateRequests requests = getAttendanceCreateRequests(
            scheduleId,
            List.of(
                getAttendanceCreateRequest(participantResponses.get(0).getParticipantId(), false),
                getAttendanceCreateRequest(participantResponses.get(1).getParticipantId(), false)
            )
        );
        requestToCreateAttendance(managerToken, requests);
        List<AttendanceResponse> attendanceResponses = getObjects(
            requestToFindAttendances(managerToken, scheduleId), AttendanceResponse.class
        );
        AttendanceUpdateRequests attendanceUpdateRequests = getAttendanceUpdateRequests(
            scheduleId,
            List.of(
                getAttendanceUpdateRequest(attendanceResponses.get(0).getAttendanceId(), true),
                getAttendanceUpdateRequest(attendanceResponses.get(1).getAttendanceId(), true)
            )
        );

        ExtractableResponse<Response> response = requestToUpdateAttendance(managerToken, attendanceUpdateRequests);

        assertThatUpdateAttendance(
            getObjects(requestToFindAttendances(managerToken, scheduleId), AttendanceResponse.class),
            attendanceUpdateRequests.getAttendanceUpdateRequests()
        );
        assertThatStatusIsOk(response);
    }
}
