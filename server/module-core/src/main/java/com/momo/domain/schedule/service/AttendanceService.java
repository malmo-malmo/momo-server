package com.momo.domain.schedule.service;

import com.momo.domain.schedule.dto.AttendanceCreateRequests;
import com.momo.domain.schedule.dto.AttendanceResponse;
import com.momo.domain.schedule.dto.AttendanceUpdateRequest;
import com.momo.domain.user.entity.User;
import java.util.List;

public interface AttendanceService {

    void create(User user, AttendanceCreateRequests requests);

    void update(User user, AttendanceUpdateRequest request);

    List<AttendanceResponse> findGroupAttendances(User user, Long groupId);
}