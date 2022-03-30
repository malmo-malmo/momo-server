package com.momo.user.application.dto;

import com.momo.user.application.dto.response.UniversityResponseDto;
import com.momo.user.domain.dto.UniversityDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UniversityDtoMapper {

    public List<UniversityResponseDto> mapToUniversityResponseDtos(List<UniversityDto> dtos) {
        return dtos.stream()
            .map(dto -> new UniversityResponseDto(dto.getUniversityName()))
            .collect(Collectors.toList());
    }
}
