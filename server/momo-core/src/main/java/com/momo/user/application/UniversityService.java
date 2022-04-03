package com.momo.user.application;

import com.momo.user.application.dto.UniversityAssembler;
import com.momo.user.application.dto.response.UniversityResponse;
import com.momo.user.domain.repository.UniversitySearchRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UniversityService {

    private final UniversitySearchRepository universitySearchRepository;

    public List<UniversityResponse> findUniversityNames(String universityName) {
        List<String> universityNames = universitySearchRepository.searchUniversityNamesByName(universityName);

        return UniversityAssembler.mapToUniversityResponses(universityNames);
    }
}
