package com.momo.district.repository;


import com.momo.district.entity.City;
import com.momo.district.entity.District;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Long> {

    List<District> findAllByCity(City city);
}
