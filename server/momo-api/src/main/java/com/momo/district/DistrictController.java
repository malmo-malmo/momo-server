package com.momo.district;

import com.momo.domain.common.dto.EnumResponse;
import com.momo.domain.district.dto.DistrictResponse;
import com.momo.domain.district.entity.City;
import com.momo.domain.district.entity.District;
import com.momo.domain.district.repository.DistrictRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/district")
@RequiredArgsConstructor
public class DistrictController {

    private final DistrictRepository districtRepository;

    @GetMapping("/cities")
    public ResponseEntity<List<EnumResponse>> findCities() {
        return ResponseEntity.ok(EnumResponse.listOfCity());
    }

    @GetMapping("/districts")
    public ResponseEntity<List<DistrictResponse>> findDistrictsByCity(@RequestParam String cityCode) {
        List<District> districts = districtRepository.findAllByCity(City.fromCode(cityCode));
        return ResponseEntity.ok(DistrictResponse.listOf(districts));
    }
}
