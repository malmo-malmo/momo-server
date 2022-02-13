package com.momo.domain.district.entity;

import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import java.util.Arrays;

public enum City {
    SEOUL("서울"),
    BUSAN("부산"),
    DAEGU("대구"),
    INCHEON("인천"),
    GWANGJU("광주"),
    DAEJEON("대전"),
    ULSAN("울산"),
    SEJONG("세종"),
    GYEONGGI("경기"),
    GANGWON("강원"),
    CHUNGBUK("충북"),
    CHUNGNAM("충남"),
    JEONBUK("전북"),
    JEONNAM("전남"),
    GYEONGBUK("경북"),
    GYEONGNAM("경남"),
    JEJU("제주");

    private final String name;

    City(String name) {
        this.name = name;
    }

    public String getCode() {
        return name();
    }

    public String getName() {
        return name;
    }

    public static City fromName(String name) {
        return Arrays.stream(City.values())
            .filter(v -> v.name.equals(name))
            .findFirst()
            .orElseThrow(() -> new CustomException(ErrorCode.CITY_CATEGORY_NAME_NOT_FOUND));
    }

    public static City fromCode(String code) {
        return Arrays.stream(City.values())
            .filter(v -> v.name().equals(code))
            .findFirst()
            .orElseThrow(() -> new CustomException(ErrorCode.CITY_CATEGORY_CODE_NOT_FOUND));
    }
}
