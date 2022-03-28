package com.momo.domain.schedule.service;

import com.momo.domain.schedule.dto.GroupScheduleResponse;
import com.momo.domain.schedule.dto.GroupScheduleResponses;
import com.momo.domain.schedule.dto.GroupSchedulesRequest;
import com.momo.domain.schedule.dto.ScheduleCreateRequest;
import com.momo.domain.schedule.dto.UpcomingScheduleResponse;
import com.momo.domain.schedule.dto.UserScheduleResponse;
import com.momo.domain.schedule.dto.UserSchedulesRequest;
import com.momo.domain.user.domain.User;
import java.util.List;

public interface ScheduleService {

    GroupScheduleResponse createSchedule(User user, ScheduleCreateRequest request);

    GroupScheduleResponses findPageByUserAndGroupId(User user, GroupSchedulesRequest request);

    List<UserScheduleResponse> findPageByUserAndSearchDate(User user, UserSchedulesRequest request);

    UpcomingScheduleResponse findUpcomingScheduleByGroupId(User user, Long groupId);
}
