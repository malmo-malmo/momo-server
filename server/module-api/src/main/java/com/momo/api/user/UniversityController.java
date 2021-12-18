package com.momo.api.user;

import com.momo.domain.user.dto.UniversityResponse;
import com.momo.domain.user.service.UniversityService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "대학교 목록 조회")
    @GetMapping("/universities")
    public ResponseEntity<List<UniversityResponse>> findByUniversityName(@RequestParam String universityName) {
        return ResponseEntity.ok(universityService.findByUniversityName(universityName));
    }
}
