package com.momo.user.application;

import com.momo.user.application.dto.UniversityDtoAssembler;
import com.momo.user.application.dto.response.UniversityResponseDto;
import com.momo.user.domain.dto.UniversityDto;
import com.momo.user.domain.repository.UniversitySearchRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {

    private final UniversitySearchRepository universitySearchRepository;

    public List<UniversityResponseDto> findUniversity(String universityName) {
        List<UniversityDto> universityDtos = universitySearchRepository.searchUniversitiesByName(universityName);

        return UniversityDtoAssembler.mapToUniversityResponseDtos(universityDtos);
    }
}
