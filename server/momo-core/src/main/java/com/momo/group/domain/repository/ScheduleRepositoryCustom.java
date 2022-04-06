package com.momo.group.domain.repository;

import com.momo.group.domain.Group;
import com.momo.group.application.dto.response.GroupScheduleResponse;
import com.momo.group.domain.schedule.Schedule;
import com.momo.user.domain.User;
import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepositoryCustom {

    List<GroupScheduleResponse> findAllByGroupOrderByStartDateTimeDesc(
        Group group, Long userId, LocalDateTime lastScheduleStartDateTime, int size
    );

    List<Schedule> findAllByStartDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, User user);

    GroupScheduleResponse findGroupResponseById(Long scheduleId, Long userId);
}
