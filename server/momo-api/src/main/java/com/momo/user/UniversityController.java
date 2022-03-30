package com.momo.user;

import com.momo.user.application.UniversityServiceImpl;
import com.momo.user.application.dto.response.UniversityResponseDto;
import com.momo.user.dto.UserMapper;
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

    private final UniversityServiceImpl universityService;
    private final UserMapper userMapper;

    @GetMapping("/universities")
    public ResponseEntity<List<UniversityResponse>> findUniversity(
        @RequestParam String universityName
    ) {
        List<UniversityResponseDto> responseDtos = universityService.findUniversity(universityName);

        return ResponseEntity.ok(userMapper.mapToUniversityResponses(responseDtos));
    }
}
