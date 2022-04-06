package com.momo.group;

import static org.springframework.http.HttpStatus.CREATED;

import com.momo.auth.CurrentUser;
import com.momo.group.application.dto.request.AttendanceCreateRequests;
import com.momo.group.application.dto.request.AttendanceUpdateRequests;
import com.momo.schedule.service.AttendanceService;
import com.momo.user.domain.User;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<Void> create(@CurrentUser User user, @Valid @RequestBody AttendanceCreateRequests requests) {
        attendanceService.createScheduleAttendances(user, requests);
        return ResponseEntity.status(CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@CurrentUser User user, @Valid @RequestBody AttendanceUpdateRequests requests) {
        attendanceService.updateScheduleAttendances(user, requests);
        return ResponseEntity.ok().build();
    }
}
