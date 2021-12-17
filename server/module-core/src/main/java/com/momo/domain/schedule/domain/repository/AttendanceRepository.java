package com.momo.domain.schedule.domain.repository;

import com.momo.domain.schedule.domain.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}