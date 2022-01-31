package com.momo.schedule.acceptance;

import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsOk;
import static com.momo.fixture.AttendanceFixture.getAttendanceCreateRequest;
import static com.momo.fixture.AttendanceFixture.getAttendanceCreateRequests;
import static com.momo.fixture.AttendanceFixture.getAttendanceUpdateRequest;
import static com.momo.fixture.AttendanceFixture.getAttendanceUpdateRequests;
import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST1;
import static com.momo.fixture.ScheduleFixture.getScheduleCreateRequest1;
import static com.momo.fixture.UserFixture.getUser1;
import static com.momo.fixture.UserFixture.getUser2;
import static com.momo.fixture.UserFixture.getUser3;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToCreateGroup;
import static com.momo.group.acceptance.step.ParticipantAcceptanceStep.requestToApplyParticipant;
import static com.momo.group.acceptance.step.ParticipantAcceptanceStep.requestToFindParticipants;
import static com.momo.schedule.acceptance.step.AttendanceAcceptanceStep.assertThatFindAttendance;
import static com.momo.schedule.acceptance.step.AttendanceAcceptanceStep.requestToAttendances;
import static com.momo.schedule.acceptance.step.AttendanceAcceptanceStep.requestToCreateAttendance;
import static com.momo.schedule.acceptance.step.AttendanceAcceptanceStep.requestToUpdateAttendance;
import static com.momo.schedule.acceptance.step.ScheduleAcceptanceStep.requestToCreateSchedule;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToFindMyInformation;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.domain.group.entity.Participant;
import com.momo.domain.group.repository.ParticipantRepository;
import com.momo.domain.schedule.dto.AttendanceCreateRequests;
import com.momo.domain.schedule.dto.AttendanceResponse;
import com.momo.domain.schedule.dto.AttendanceUpdateRequests;
import com.momo.domain.schedule.entity.Attendance;
import com.momo.domain.schedule.repository.AttendanceRepository;
import com.momo.domain.user.dto.UserResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("출석 통합/인수 테스트")
public class AttendanceAcceptanceTest extends AcceptanceTest {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Test
    void 모임_관리자가_일정_출석_체크를_한다() {
        String managerToken = getAccessToken(getUser1());
        String userToken1 = getAccessToken(getUser2());
        String userToken2 = getAccessToken(getUser3());

        Long groupId = extractId(requestToCreateGroup(managerToken, GROUP_CREATE_REQUEST1));

        Long participantId1 = extractId(requestToApplyParticipant(userToken1, groupId));
        Long participantId2 = extractId(requestToApplyParticipant(userToken2, groupId));

        Long scheduleId = extractId(requestToCreateSchedule(managerToken, getScheduleCreateRequest1(groupId)));

        Long managerId = getObject(requestToFindMyInformation(managerToken), UserResponse.class).getId();

        AttendanceCreateRequests attendanceCreateRequests = getAttendanceCreateRequests(
            scheduleId,
            List.of(
                getAttendanceCreateRequest(managerId, true),
                getAttendanceCreateRequest(participantId1, true),
                getAttendanceCreateRequest(participantId2, false)
            )
        );

        requestToCreateAttendance(managerToken, attendanceCreateRequests);
        requestToFindParticipants(managerToken, groupId);
    }

    @Test
    void 모임_관리자가_일정_출석체크_목록을_조회한다() {
        String managerToken = getAccessToken(getUser1());
        String userToken1 = getAccessToken(getUser2());
        String userToken2 = getAccessToken(getUser3());

        Long groupId = extractId(requestToCreateGroup(managerToken, GROUP_CREATE_REQUEST1));

        Long participantId1 = extractId(requestToApplyParticipant(userToken1, groupId));
        Long participantId2 = extractId(requestToApplyParticipant(userToken2, groupId));

        Long scheduleId = extractId(requestToCreateSchedule(managerToken, getScheduleCreateRequest1(groupId)));

        AttendanceCreateRequests attendanceCreateRequests = getAttendanceCreateRequests(
            scheduleId,
            List.of(
                getAttendanceCreateRequest(participantId1, true),
                getAttendanceCreateRequest(participantId2, false)
            )
        );

        requestToCreateAttendance(managerToken, attendanceCreateRequests);

        ExtractableResponse<Response> response = requestToAttendances(managerToken, scheduleId);
        List<AttendanceResponse> attendanceResponses = getObjects(response, AttendanceResponse.class);
        assertThatStatusIsOk(response);
        assertThatFindAttendance(attendanceCreateRequests.getAttendanceCreateRequests(), attendanceResponses);
    }

    @Test
    void 모임_관리자가_출석_체크를_수정한다() {
        String managerToken = getAccessToken(getUser1());
        String userToken1 = getAccessToken(getUser2());
        String userToken2 = getAccessToken(getUser3());

        Long groupId = extractId(requestToCreateGroup(managerToken, GROUP_CREATE_REQUEST1));

        Long participantId1 = extractId(requestToApplyParticipant(userToken1, groupId));
        Long participantId2 = extractId(requestToApplyParticipant(userToken2, groupId));

        Long scheduleId = extractId(requestToCreateSchedule(managerToken, getScheduleCreateRequest1(groupId)));

        Long managerId = getObject(requestToFindMyInformation(managerToken), UserResponse.class).getId();

        AttendanceCreateRequests attendanceCreateRequests = getAttendanceCreateRequests(
            scheduleId,
            List.of(
                getAttendanceCreateRequest(participantId1, true),
                getAttendanceCreateRequest(participantId2, false)
            )
        );
        requestToCreateAttendance(managerToken, attendanceCreateRequests);

        ExtractableResponse<Response> attendanceResponse = requestToAttendances(managerToken, scheduleId);
        List<AttendanceResponse> attendanceResponses = getObjects(attendanceResponse, AttendanceResponse.class);

        Long targetAttendanceId = attendanceResponses.get(0).getAttendanceId();
        AttendanceUpdateRequests attendanceUpdateRequests = getAttendanceUpdateRequests(
            scheduleId,
            List.of(
                getAttendanceUpdateRequest(targetAttendanceId, false)
            )
        );
        ExtractableResponse<Response> updateResponse = requestToUpdateAttendance(managerToken,
            attendanceUpdateRequests);
        assertThatStatusIsOk(updateResponse);

        Attendance attendance = attendanceRepository.findById(targetAttendanceId).get();
        assertThat(attendance.isAttend()).isFalse();
    }
}
