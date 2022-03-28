package com.momo.domain.schedule.service;

import com.momo.domain.schedule.dto.AttendanceCreateRequests;
import com.momo.domain.schedule.dto.AttendanceResponse;
import com.momo.domain.schedule.dto.AttendanceUpdateRequests;
import com.momo.domain.user.domain.User;
import java.util.List;

public interface AttendanceService {

    void createScheduleAttendances(User user, AttendanceCreateRequests requests);

    void updateScheduleAttendances(User user, AttendanceUpdateRequests requests);

    List<AttendanceResponse> findScheduleAttendances(User user, Long scheduleId);
}
