package com.momo.domain.schedule.domain.repository;

import com.momo.domain.group.domain.model.Groups;
import com.momo.domain.schedule.domain.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>, ScheduleRepositoryCustom {

    @Query("select count(s) from Schedule s where s.group = ?1 and s.attendanceCheck = ?2")
    Long countByGroupAndAttendanceCheck(Groups group, boolean attendanceCheck);
}
