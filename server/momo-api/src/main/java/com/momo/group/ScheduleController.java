package com.momo.group;

import com.momo.auth.CurrentUser;
import com.momo.group.application.ScheduleService;
import com.momo.group.application.dto.request.GroupSchedulesRequest;
import com.momo.group.application.dto.request.ScheduleCreateRequest;
import com.momo.group.application.dto.request.UserSchedulesRequest;
import com.momo.group.application.dto.response.GroupScheduleResponse;
import com.momo.group.application.dto.response.GroupScheduleResponses;
import com.momo.group.application.dto.response.UpcomingScheduleResponse;
import com.momo.group.application.dto.response.UserScheduleResponse;
import com.momo.user.domain.User;
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
