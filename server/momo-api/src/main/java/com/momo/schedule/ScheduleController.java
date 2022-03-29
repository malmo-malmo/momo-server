package com.momo.schedule;

import com.momo.auth.CurrentUser;
import com.momo.domain.schedule.dto.GroupScheduleResponse;
import com.momo.domain.schedule.dto.GroupScheduleResponses;
import com.momo.domain.schedule.dto.GroupSchedulesRequest;
import com.momo.domain.schedule.dto.ScheduleCreateRequest;
import com.momo.domain.schedule.dto.UpcomingScheduleResponse;
import com.momo.domain.schedule.dto.UserScheduleResponse;
import com.momo.domain.schedule.dto.UserSchedulesRequest;
import com.momo.domain.schedule.service.ScheduleService;
import com.momo.domain.user.entity.User;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<GroupScheduleResponse> createGroupSchedule(
        @CurrentUser User user,
        @Valid @RequestBody ScheduleCreateRequest request
    ) throws URISyntaxException {
        GroupScheduleResponse response = scheduleService.createSchedule(user, request);
        return ResponseEntity.created(new URI("/api/schedule/" + response.getScheduleId())).body(response);
    }

    @GetMapping("/group-schedules")
    public ResponseEntity<GroupScheduleResponses> findGroupSchedulePage(
        @CurrentUser User user,
        @Valid @ModelAttribute GroupSchedulesRequest request
    ) {
        GroupScheduleResponses response = scheduleService.findPageByUserAndGroupId(user, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user-schedules")
    public ResponseEntity<List<UserScheduleResponse>> findUserSchedulePage(
        @CurrentUser User user,
        @Valid @ModelAttribute UserSchedulesRequest request
    ) {
        List<UserScheduleResponse> response = scheduleService.findPageByUserAndSearchDate(user, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/upcoming")
    public ResponseEntity<UpcomingScheduleResponse> findUpcomingSchedule(
        @CurrentUser User user,
        @RequestParam Long groupId
    ) {
        UpcomingScheduleResponse response = scheduleService.findUpcomingScheduleByGroupId(user, groupId);
        return ResponseEntity.ok(response);
    }
}
