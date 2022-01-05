package com.momo.domain.schedule.repository;

import com.momo.domain.group.entity.Groups;
import com.momo.domain.schedule.entity.Schedule;
import com.momo.domain.schedule.dto.GroupScheduleResponse;
import com.momo.domain.user.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ScheduleRepositoryCustom {

    List<GroupScheduleResponse> findAllByGroupAndUserOrderByCreatedDateDesc(Groups group, Long userId,
                                                                            Pageable pageable);

    List<Schedule> findAllByStartDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, User user);
}