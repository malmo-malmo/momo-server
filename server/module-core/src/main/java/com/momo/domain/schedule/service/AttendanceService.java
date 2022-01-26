package com.momo.domain.schedule.service;

import com.momo.domain.schedule.dto.AttendanceCreateRequests;
import com.momo.domain.user.entity.User;

public interface AttendanceService {

    void create(User user, AttendanceCreateRequests requests);

}