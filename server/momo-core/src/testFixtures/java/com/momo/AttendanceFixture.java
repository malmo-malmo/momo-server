package com.momo;

import static com.momo.common.FixtureComponents.INCREASE_ID;

import com.momo.domain.group.entity.Participant;
import com.momo.domain.schedule.dto.AttendanceCreateRequest;
import com.momo.domain.schedule.dto.AttendanceCreateRequests;
import com.momo.domain.schedule.dto.AttendanceUpdateRequest;
import com.momo.domain.schedule.dto.AttendanceUpdateRequests;
import com.momo.domain.schedule.entity.Attendance;
import com.momo.domain.schedule.entity.Schedule;
import java.util.List;

public class AttendanceFixture {

    public static Attendance getAttendance(Schedule schedule, Participant participant, boolean isAttend) {
        return Attendance.builder()
            .schedule(schedule)
            .participant(participant)
            .isAttend(isAttend)
            .build();
    }

    public static Attendance getAttendanceWithId(Schedule schedule, Participant participant, boolean isAttend) {
        INCREASE_ID++;
        return Attendance.builder()
            .id(INCREASE_ID)
            .schedule(schedule)
            .participant(participant)
            .isAttend(isAttend)
            .build();
    }

    public static AttendanceCreateRequest getAttendanceCreateRequest(Long participantId, boolean isAttend) {
        return AttendanceCreateRequest.builder()
            .participantId(participantId)
            .isAttend(isAttend)
            .build();
    }

    public static AttendanceCreateRequests getAttendanceCreateRequests(Long scheduleId,
        List<AttendanceCreateRequest> requests) {
        return AttendanceCreateRequests.builder()
            .scheduleId(scheduleId)
            .attendanceCreateRequests(requests)
            .build();
    }

    public static AttendanceUpdateRequests getAttendanceUpdateRequests(Long scheduleId,
        List<AttendanceUpdateRequest> requests) {
        return AttendanceUpdateRequests.builder()
            .scheduleId(scheduleId)
            .attendanceUpdateRequests(requests)
            .build();
    }

    public static AttendanceUpdateRequest getAttendanceUpdateRequest(Long attendanceId, boolean isAttend) {
        return AttendanceUpdateRequest.builder()
            .attendanceId(attendanceId)
            .isAttend(isAttend)
            .build();
    }
}
