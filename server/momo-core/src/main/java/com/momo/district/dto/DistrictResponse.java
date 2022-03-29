package com.momo.district.dto;

import com.momo.district.entity.District;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictResponse {

    private String districtName;

    private static DistrictResponse of(District district) {
        return new DistrictResponse(district.getDistrictName());
    }

    public static List<DistrictResponse> listOf(List<District> districts) {
        return districts.stream().map(DistrictResponse::of).collect(Collectors.toList());
    }
}
