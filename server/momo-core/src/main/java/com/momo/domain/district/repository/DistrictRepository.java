package com.momo.domain.district.repository;


import com.momo.domain.district.entity.City;
import com.momo.domain.district.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Long> {
    List<District> findAllByCity(City city);
}
