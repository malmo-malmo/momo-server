package com.momo.group.domain.repository;

import com.momo.group.domain.schedule.attendance.Attendance;
import java.util.List;

public interface AttendanceRepositoryCustom {

    List<Attendance> findAllByIds(List<Long> attendanceIds);
}
