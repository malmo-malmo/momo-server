package com.momo.user.application;

import com.momo.user.application.dto.response.UniversityResponseDto;
import java.util.List;

public interface UniversityService {

    List<UniversityResponseDto> findUniversity(String universityName);
}
