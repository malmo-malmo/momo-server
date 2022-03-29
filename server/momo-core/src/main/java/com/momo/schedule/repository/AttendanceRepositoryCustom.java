package com.momo.schedule.repository;

import com.momo.schedule.entity.Attendance;
import java.util.List;

public interface AttendanceRepositoryCustom {

    List<Attendance> findAllByIds(List<Long> attendanceIds);
}
