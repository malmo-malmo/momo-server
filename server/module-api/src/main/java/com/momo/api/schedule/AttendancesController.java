package com.momo.api.schedule;

import com.momo.common.CurrentUser;
import com.momo.domain.schedule.dto.AttendanceResponse;
import com.momo.domain.schedule.service.AttendanceService;
import com.momo.domain.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/attendances")
@RequiredArgsConstructor
public class AttendancesController {

    private final AttendanceService attendanceService;

    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<List<AttendanceResponse>> findGroupAttendances(@CurrentUser User user,
        @PathVariable Long scheduleId) {
        List<AttendanceResponse> responses = attendanceService.findScheduleAttendances(user, scheduleId);
        return ResponseEntity.ok(responses);
    }
}