package com.momo.user.domain;

import com.momo.user.domain.dto.UniversityDto;
import java.util.List;

public interface UniversitySearchOpenApi {

    List<UniversityDto> search(String universityName);
}
