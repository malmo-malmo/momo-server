package com.momo.domain.district.domain.repository;


import com.momo.domain.district.domain.model.City;
import com.momo.domain.district.domain.model.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Long> {
    List<District> findAllByCity(City city);
}
