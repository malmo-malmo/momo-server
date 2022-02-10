package com.momo.domain.schedule.repository;

import com.momo.domain.schedule.entity.Attendance;
import java.util.List;

public interface AttendanceRepositoryCustom {

    List<Attendance> findAllByIds(List<Long> attendanceIds);
}
