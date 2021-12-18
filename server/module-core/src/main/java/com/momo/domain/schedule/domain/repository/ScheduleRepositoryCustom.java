package com.momo.domain.schedule.domain.repository;

import com.momo.domain.group.domain.model.Groups;
import com.momo.domain.schedule.domain.model.Schedule;
import com.momo.domain.schedule.dto.GroupScheduleResponse;
import com.momo.domain.user.domain.model.User;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ScheduleRepositoryCustom {

    List<GroupScheduleResponse> findAllByGroupAndUserOrderByCreatedDateDesc(Groups group, Long userId,
                                                                            Pageable pageable);

    List<Schedule> findAllByStartDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, User user);
}
