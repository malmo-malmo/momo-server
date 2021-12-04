package com.momo.district.controller;

import com.momo.common.dto.EnumResponse;
import com.momo.district.controller.dto.DistrictResponse;
import com.momo.district.domain.model.City;
import com.momo.district.domain.model.District;
import com.momo.district.domain.repository.DistrictRepository;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DistrictController {

    private final DistrictRepository districtRepository;

    @ApiOperation(value = "시/도 목록 조회")
    @GetMapping("/district/cities")
    public ResponseEntity<List<EnumResponse>> findCities() {
        return ResponseEntity.ok(EnumResponse.listOfCity());
    }

    @ApiOperation(value = "구/군 목록 조회")
    @GetMapping("/district/districts")
    public ResponseEntity<List<DistrictResponse>> findDistrictsByCity(@RequestParam String cityCode) {
        List<District> districts = districtRepository.findAllByCity(City.of(cityCode));
        return ResponseEntity.ok(DistrictResponse.listOf(districts));
    }
}
