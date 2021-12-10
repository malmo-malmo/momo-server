package com.momo.schedule.domain.repository;

import com.momo.group.domain.model.Groups;
import com.momo.schedule.controller.dto.GroupScheduleResponse;
import com.momo.schedule.domain.model.Schedule;
import com.momo.user.domain.model.User;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ScheduleRepositoryCustom {

    List<GroupScheduleResponse> findAllByGroupAndUserOrderByCreatedDateDesc(Groups group, Long userId,
        Pageable pageable);

    List<Schedule> findAllByStartDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, User user);
}
