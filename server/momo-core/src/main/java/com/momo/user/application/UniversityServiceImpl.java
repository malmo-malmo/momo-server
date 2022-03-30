package com.momo.user.application;

import com.momo.user.application.dto.UserDtoMapper;
import com.momo.user.application.dto.response.UniversityResponseDto;
import com.momo.user.domain.UniversitySearchOpenApi;
import com.momo.user.domain.dto.UniversityDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {

    private final UniversitySearchOpenApi universitySearchOpenApi;
    private final UserDtoMapper userDtoMapper;

    public List<UniversityResponseDto> findUniversity(String universityName) {
        List<UniversityDto> universityDtos = universitySearchOpenApi.search(universityName);

        return userDtoMapper.mapToUniversityResponseDtos(universityDtos);
    }
}
