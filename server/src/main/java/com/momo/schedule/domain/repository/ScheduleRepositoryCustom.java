package com.momo.schedule.domain.repository;

import com.momo.group.domain.model.Groups;
import com.momo.schedule.controller.dto.GroupScheduleResponse;
import com.momo.user.domain.model.User;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ScheduleRepositoryCustom {

    List<GroupScheduleResponse> findAllByGroupAndUserOrderByCreatedDateDesc(Groups group, User user, Pageable pageable);
}
