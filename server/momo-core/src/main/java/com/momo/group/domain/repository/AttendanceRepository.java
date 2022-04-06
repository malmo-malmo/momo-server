package com.momo.group.domain.repository;

import com.momo.group.domain.schedule.attendance.Attendance;
import com.momo.group.domain.schedule.Schedule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>, AttendanceRepositoryCustom {

    List<Attendance> findBySchedule(Schedule schedule);
}
