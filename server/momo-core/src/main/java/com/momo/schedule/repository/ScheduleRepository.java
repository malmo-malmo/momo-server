package com.momo.schedule.repository;

import com.momo.group.entity.Group;
import com.momo.schedule.entity.Schedule;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>, ScheduleRepositoryCustom {

    @Query("select s from Schedule s join fetch s.group where s.id = ?1")
    Optional<Schedule> findScheduleWithGroupById(Long id);

    Schedule findFirstByGroupAndStartDateTimeAfter(Group group, LocalDateTime now);
}
