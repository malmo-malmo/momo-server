package com.momo.user.dto;

import com.momo.user.application.dto.response.UniversityResponseDto;
import com.momo.user.dto.response.UniversityResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UniversityMapper {

    public List<UniversityResponse> mapToUniversityResponses(List<UniversityResponseDto> dtos) {
        return dtos.stream()
            .map(dto -> new UniversityResponse(dto.getName()))
            .collect(Collectors.toList());
    }
}
