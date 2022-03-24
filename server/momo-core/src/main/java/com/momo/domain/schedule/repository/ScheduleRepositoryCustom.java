package com.momo.domain.schedule.repository;

import com.momo.domain.group.entity.Group;
import com.momo.domain.schedule.dto.GroupScheduleResponse;
import com.momo.domain.schedule.entity.Schedule;
import com.momo.domain.user.entity.User;
import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepositoryCustom {

    List<GroupScheduleResponse> findAllByGroupOrderByStartDateTimeDesc(
        Group group, Long userId, LocalDateTime lastScheduleStartDateTime, int size
    );

    List<Schedule> findAllByStartDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, User user);

    GroupScheduleResponse findGroupResponseById(Long scheduleId, Long userId);
}
