package com.momo.user.controller;

import com.momo.user.controller.dto.UniversityResponse;
import com.momo.user.service.UniversityService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/university")
@RequiredArgsConstructor
public class UniversityController {

    private final UniversityService universityService;

    @GetMapping
    public ResponseEntity<List<UniversityResponse>> find(@RequestParam String universityName) {
        return ResponseEntity.ok(universityService.find(universityName));
    }
}
