package com.momo.domain.schedule.repository;

import com.momo.domain.group.entity.Group;
import com.momo.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>, ScheduleRepositoryCustom {

    @Query("select count(s) from Schedule s where s.group = ?1 and s.attendanceCheck = ?2")
    Long countByGroupAndAttendanceCheck(Group group, boolean attendanceCheck);
}
