package com.momo.domain.schedule.repository;

import com.momo.domain.schedule.entity.Schedule;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>, ScheduleRepositoryCustom {

    @Query("select s from Schedule s join fetch s.group where s.id = ?1")
    Optional<Schedule> findScheduleWithGroupById(Long id);
}