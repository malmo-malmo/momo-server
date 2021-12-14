package com.momo.schedule.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.momo.auth.domain.CurrentUser;
import com.momo.schedule.controller.dto.AttendanceCreateRequests;
import com.momo.schedule.service.AttendanceService;
import com.momo.user.domain.model.User;
import io.swagger.annotations.ApiOperation;
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
public class AttendanceController {

    private final AttendanceService attendanceService;

    @ApiOperation(value = "출석 체크")
    @PostMapping("/attendance")
    public ResponseEntity<Void> create(@CurrentUser User user,
        @Valid @RequestBody AttendanceCreateRequests attendanceCreateRequests) {
        attendanceService.create(user, attendanceCreateRequests);
        return ResponseEntity.status(CREATED).build();
    }
}
