package com.momo.schedule.service;

import com.momo.schedule.dto.AttendanceCreateRequests;
import com.momo.schedule.dto.AttendanceResponse;
import com.momo.schedule.dto.AttendanceUpdateRequests;
import com.momo.user.entity.User;
import java.util.List;

public interface AttendanceService {

    void createScheduleAttendances(User user, AttendanceCreateRequests requests);

    void updateScheduleAttendances(User user, AttendanceUpdateRequests requests);

    List<AttendanceResponse> findScheduleAttendances(User user, Long scheduleId);
}