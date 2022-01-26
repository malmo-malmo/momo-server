package com.momo.api.schedule;

import com.momo.common.CurrentUser;
import com.momo.domain.schedule.dto.GroupScheduleResponse;
import com.momo.domain.schedule.dto.GroupScheduleResponses;
import com.momo.domain.schedule.dto.GroupSchedulesRequest;
import com.momo.domain.schedule.dto.ScheduleCreateRequest;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<GroupScheduleResponse> create(@CurrentUser User user,
        @Valid @RequestBody ScheduleCreateRequest scheduleCreateRequest) throws URISyntaxException {
        GroupScheduleResponse response = scheduleService.create(user, scheduleCreateRequest);
        return ResponseEntity.created(new URI("/api/schedule/" + response.getId())).body(response);
    }

    @GetMapping("/group-schedules")
    public ResponseEntity<GroupScheduleResponses> findPageByGroup(@CurrentUser User user,
        @ModelAttribute @Valid GroupSchedulesRequest groupSchedulesRequest) {
        GroupScheduleResponses response = scheduleService.findPageByUserAndGroupId(user, groupSchedulesRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user-schedules")
    public ResponseEntity<List<UserScheduleResponse>> findPageByUser(@CurrentUser User user,
        @ModelAttribute @Valid UserSchedulesRequest userSchedulesRequest) {
        List<UserScheduleResponse> response = scheduleService.findPageByUserAndSearchDate(user, userSchedulesRequest);
        return ResponseEntity.ok(response);
    }
}
