package com.momo.common.dto;

import com.momo.district.domain.model.City;
import com.momo.group.domain.model.Category;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EnumResponse {

    private String code;
    private String name;

    private EnumResponse(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private static EnumResponse of(String code, String name) {
        return new EnumResponse(code, name);
    }

    public static EnumResponse ofCategory(Category category) {
        return new EnumResponse(category.getCode(), category.getName());
    }

    public static List<EnumResponse> listOfCategory() {
        return Arrays.stream(Category.values())
            .map(status -> EnumResponse.of(status.getCode(), status.getName()))
            .collect(Collectors.toList());
    }

    public static List<EnumResponse> listOfCity() {
        return Arrays.stream(City.values())
            .map(status -> EnumResponse.of(status.getCode(), status.getName()))
            .collect(Collectors.toList());
    }
}
