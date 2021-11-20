package com.momo.schedule.controller;

import com.momo.schedule.controller.dto.GroupSchedulesResponse;
import com.momo.schedule.controller.dto.ScheduleCreateRequest;
import com.momo.schedule.service.ScheduleService;
import com.momo.security.CurrentUser;
import com.momo.user.domain.model.User;
import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedule")
    public ResponseEntity<Void> create(@CurrentUser User user,
        @Valid @RequestBody ScheduleCreateRequest scheduleCreateRequest) throws URISyntaxException {
        Long scheduleId = scheduleService.create(user, scheduleCreateRequest);
        return ResponseEntity.created(new URI("/api/schedule/" + scheduleId)).build();
    }

    @GetMapping("/schedules/group/{groupId}")
    public ResponseEntity<GroupSchedulesResponse> findPageByGroup(@CurrentUser User user, @PathVariable Long groupId,
        @RequestParam int page, @RequestParam int size) {
        GroupSchedulesResponse response = scheduleService.findPageByGroupAndUser(user, groupId, page, size);
        return ResponseEntity.ok(response);
    }
}
