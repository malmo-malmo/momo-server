package com.momo.user.domain.repository;

import java.util.List;

public interface UniversitySearchRepository {

    List<String> searchUniversityNamesByName(String universityName);
}
