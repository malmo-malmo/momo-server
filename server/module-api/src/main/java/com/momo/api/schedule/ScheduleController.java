package com.momo.api.schedule;

import com.momo.common.CurrentUser;
import com.momo.domain.schedule.dto.*;
import com.momo.domain.schedule.service.ScheduleService;
import com.momo.domain.user.entity.User;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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
    public ResponseEntity<GroupScheduleResponses> findPageByGroup(@CurrentUser User user,
                                                                  @ModelAttribute @Valid GroupSchedulesRequest groupSchedulesRequest) {
        GroupScheduleResponses response = scheduleService.findPageByUserAndGroupId(user, groupSchedulesRequest);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "캘린더 일정 조회")
    @GetMapping("/schedule/user-schedules")
    public ResponseEntity<List<UserScheduleResponse>> findPageByUser(@CurrentUser User user,
                                                                     @ModelAttribute @Valid UserSchedulesRequest userSchedulesRequest) {
        List<UserScheduleResponse> response = scheduleService.findPageByUserAndSearchDate(user, userSchedulesRequest);
        return ResponseEntity.ok(response);
    }
}
