package com.momo.domain.schedule.repository;

import com.momo.domain.schedule.entity.Attendance;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Optional<Attendance> findByUserId(Long userId);
}