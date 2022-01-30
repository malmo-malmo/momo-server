package com.momo.domain.schedule.service;

import com.momo.domain.schedule.dto.AttendanceCreateRequests;
import com.momo.domain.schedule.dto.AttendanceResponse;
import com.momo.domain.schedule.dto.AttendanceUpdateRequests;
import com.momo.domain.user.entity.User;
import java.util.List;

public interface AttendanceService {

    void creates(User user, AttendanceCreateRequests requests);

    void updates(User user, AttendanceUpdateRequests requests);

    List<AttendanceResponse> findScheduleAttendances(User user, Long scheduleId);
}