package com.momo.user.application.dto;

import com.momo.user.application.dto.response.UniversityResponse;
import java.util.List;
import java.util.stream.Collectors;

public class UniversityAssembler {

    public static List<UniversityResponse> mapToUniversityResponses(List<String> universityNames) {
        return universityNames.stream()
            .map(UniversityResponse::new)
            .collect(Collectors.toList());
    }
}
