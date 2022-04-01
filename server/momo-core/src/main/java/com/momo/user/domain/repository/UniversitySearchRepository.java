package com.momo.user.domain.repository;

import com.momo.user.domain.dto.UniversityDto;
import java.util.List;

public interface UniversitySearchRepository {

    List<UniversityDto> searchUniversitiesByName(String universityName);
}
