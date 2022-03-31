package com.momo.user.application;

import com.momo.user.application.dto.UniversityDtoAssembler;
import com.momo.user.application.dto.response.UniversityResponseDto;
import com.momo.user.domain.UniversitySearchClient;
import com.momo.user.domain.dto.UniversityDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {

    private final UniversitySearchClient universitySearchClient;

    public List<UniversityResponseDto> findUniversity(String universityName) {
        List<UniversityDto> universityDtos = universitySearchClient.search(universityName);

        return UniversityDtoAssembler.mapToUniversityResponseDtos(universityDtos);
    }
}
