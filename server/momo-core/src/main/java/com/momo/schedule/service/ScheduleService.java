package com.momo.schedule.service;

import com.momo.schedule.dto.GroupScheduleResponse;
import com.momo.schedule.dto.GroupScheduleResponses;
import com.momo.schedule.dto.GroupSchedulesRequest;
import com.momo.schedule.dto.ScheduleCreateRequest;
import com.momo.schedule.dto.UpcomingScheduleResponse;
import com.momo.schedule.dto.UserScheduleResponse;
import com.momo.schedule.dto.UserSchedulesRequest;
import com.momo.user.domain.User;
import java.util.List;

public interface ScheduleService {

    GroupScheduleResponse createSchedule(User user, ScheduleCreateRequest request);

    GroupScheduleResponses findPageByUserAndGroupId(User user, GroupSchedulesRequest request);

    List<UserScheduleResponse> findPageByUserAndSearchDate(User user, UserSchedulesRequest request);

    UpcomingScheduleResponse findUpcomingScheduleByGroupId(User user, Long groupId);
}
