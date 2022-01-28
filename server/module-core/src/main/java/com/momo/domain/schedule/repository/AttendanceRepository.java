package com.momo.domain.schedule.repository;

import com.momo.domain.group.entity.Group;
import com.momo.domain.schedule.entity.Attendance;
import com.momo.domain.user.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Optional<Attendance> findByUser(User user);

    List<Attendance> findByGroup(Group group);
}