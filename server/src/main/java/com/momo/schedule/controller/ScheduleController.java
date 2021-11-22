package com.momo.schedule.controller;

import com.momo.schedule.controller.dto.GroupSchedulesRequest;
import com.momo.schedule.controller.dto.GroupSchedulesResponse;
import com.momo.schedule.controller.dto.ScheduleCreateRequest;
import com.momo.schedule.controller.dto.UserScheduleResponse;
import com.momo.schedule.controller.dto.UserSchedulesRequest;
import com.momo.schedule.service.ScheduleService;
import com.momo.security.CurrentUser;
import com.momo.user.domain.model.User;
import io.swagger.annotations.ApiOperation;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @ApiOperation(value = "일정 등록")
    @PostMapping("/schedule")
    public ResponseEntity<Void> create(@CurrentUser User user,
        @Valid @RequestBody ScheduleCreateRequest scheduleCreateRequest) throws URISyntaxException {
        Long scheduleId = scheduleService.create(user, scheduleCreateRequest);
        return ResponseEntity.created(new URI("/api/schedule/" + scheduleId)).build();
    }

    @ApiOperation(value = "모임 일정 조회")
    @GetMapping("/schedule/group-schedules")
    public ResponseEntity<GroupSchedulesResponse> findPageByGroup(@CurrentUser User user,
        @ModelAttribute @Valid GroupSchedulesRequest request) {
        GroupSchedulesResponse response = scheduleService.findPageByGroupAndUser(user, request);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "캘린더 일정 조회")
    @GetMapping("/schedule/user-schedules")
    public ResponseEntity<List<UserScheduleResponse>> findPageByUser(@CurrentUser User user,
        @ModelAttribute @Valid UserSchedulesRequest request) {
        List<UserScheduleResponse> response = scheduleService.findPageByUser(user, request);
        return ResponseEntity.ok(response);
    }
}
