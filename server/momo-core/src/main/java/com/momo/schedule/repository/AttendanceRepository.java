package com.momo.schedule.repository;

import com.momo.schedule.entity.Attendance;
import com.momo.schedule.entity.Schedule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>, AttendanceRepositoryCustom {

    List<Attendance> findBySchedule(Schedule schedule);
}