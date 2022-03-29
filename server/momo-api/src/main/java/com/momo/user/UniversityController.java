package com.momo.user;

import com.momo.user.dto.UniversityResponse;
import com.momo.user.service.UniversityService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UniversityController {

    private final UniversityService universityService;

    @GetMapping("/universities")
    public ResponseEntity<List<UniversityResponse>> findByUniversityName(@RequestParam String universityName) {
        return ResponseEntity.ok(universityService.findByUniversityName(universityName));
    }
}
