package com.momo.user;

import com.momo.user.application.UniversityService;
import com.momo.user.application.dto.response.UniversityResponse;
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

    @GetMapping("/names")
    public ResponseEntity<List<UniversityResponse>> findUniversityNames(@RequestParam String universityName) {
        List<UniversityResponse> responses = universityService.findUniversityNames(universityName);

        return ResponseEntity.ok(responses);
    }
}
