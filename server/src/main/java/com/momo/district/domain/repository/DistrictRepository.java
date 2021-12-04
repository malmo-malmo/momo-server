package com.momo.district.domain.repository;

import com.momo.district.domain.model.City;
import com.momo.district.domain.model.District;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Long> {

    List<District> findAllByCity(City city);
}
