package com.momo.domain.schedule.service;

import com.momo.domain.schedule.dto.GroupScheduleResponse;
import com.momo.domain.schedule.dto.GroupScheduleResponses;
import com.momo.domain.schedule.dto.GroupSchedulesRequest;
import com.momo.domain.schedule.dto.ScheduleCreateRequest;
import com.momo.domain.schedule.dto.UserScheduleResponse;
import com.momo.domain.schedule.dto.UserSchedulesRequest;
import com.momo.domain.user.entity.User;
import java.util.List;

public interface ScheduleService {

    GroupScheduleResponse create(User user, ScheduleCreateRequest request);

    GroupScheduleResponses findPageByUserAndGroupId(User user, GroupSchedulesRequest request);

    List<UserScheduleResponse> findPageByUserAndSearchDate(User user, UserSchedulesRequest request);
}