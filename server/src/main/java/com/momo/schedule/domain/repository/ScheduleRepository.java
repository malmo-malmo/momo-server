package com.momo.schedule.domain.repository;

import com.momo.group.domain.model.Groups;
import com.momo.schedule.domain.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>, ScheduleRepositoryCustom {

    @Query("select count(s) from Schedule s where s.group = ?1 and s.isAttendanceCheck = ?2")
    Long countByGroupAndIsAttendanceCheck(Groups group, boolean isAttendanceCheck);
}
