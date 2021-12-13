package com.momo.fixture;

import com.momo.schedule.controller.dto.AttendanceCreateRequest;
import com.momo.schedule.controller.dto.AttendanceCreateRequests;
import java.util.List;

public class AttendanceFixture {

    public static AttendanceCreateRequest getAttendanceCreateRequest(Long userId, boolean isAttend) {
        return AttendanceCreateRequest.builder()
            .userId(userId)
            .isAttend(isAttend)
            .build();
    }

    public static AttendanceCreateRequests getAttendanceCreateRequests(Long groupId, Long scheduleId,
        List<AttendanceCreateRequest> requests) {
        return AttendanceCreateRequests.builder()
            .groupId(groupId)
            .scheduleId(scheduleId)
            .attendanceCreateRequests(requests)
            .build();
    }
}
