package com.momo.api.schedule;

import static org.springframework.http.HttpStatus.CREATED;

import com.momo.common.CurrentUser;
import com.momo.domain.schedule.dto.AttendanceCreateRequests;
import com.momo.domain.schedule.dto.AttendanceUpdateRequests;
import com.momo.domain.schedule.service.AttendanceService;
import com.momo.domain.user.entity.User;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<Void> create(@CurrentUser User user,
        @Valid @RequestBody AttendanceCreateRequests attendanceCreateRequests) {
        attendanceService.createScheduleAttendances(user, attendanceCreateRequests);
        return ResponseEntity.status(CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@CurrentUser User user,
        @Valid @RequestBody AttendanceUpdateRequests attendanceUpdateRequests) {
        attendanceService.updateScheduleAttendances(user, attendanceUpdateRequests);
        return ResponseEntity.ok().build();
    }
}
