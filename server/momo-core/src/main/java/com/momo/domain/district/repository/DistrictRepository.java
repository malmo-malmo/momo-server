package com.momo.domain.district.repository;


import com.momo.domain.district.entity.City;
import com.momo.domain.district.entity.District;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Long> {

    List<District> findAllByCity(City city);
}
