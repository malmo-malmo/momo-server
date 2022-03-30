package com.momo.user;

import com.momo.user.application.UniversityService;
import com.momo.user.application.dto.response.UniversityResponseDto;
import com.momo.user.dto.UniversityAssembler;
import com.momo.user.dto.response.UniversityResponse;
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
    public ResponseEntity<List<UniversityResponse>> findUniversity(
        @RequestParam String universityName
    ) {
        List<UniversityResponseDto> responseDtos = universityService.findUniversity(universityName);

        return ResponseEntity.ok(UniversityAssembler.mapToUniversityResponses(responseDtos));
    }
}
