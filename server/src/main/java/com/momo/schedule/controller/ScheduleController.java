package com.momo.schedule.controller;

import com.momo.schedule.controller.dto.ScheduleCreateRequest;
import com.momo.schedule.service.ScheduleService;
import com.momo.security.CurrentUser;
import com.momo.user.domain.model.User;
import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
