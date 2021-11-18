package com.momo.user.domain.model;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import java.util.Arrays;
import java.util.Objects;

public enum Location {
    EUNPYEONG_GU("은평구"),
    MAPO_GU("마포구"),
    SEODAEMUN_GU("서대문구"),
    JONGNO_GU("종로구"),
    JUNG_GU("중구"),
    ONGSAN_GU("용산구"),
    SEONGBUK_GU("성북구"),
    GANGBUK_GU("강북구"),
    DOBONG_GU("도봉구"),
    DONGDAEMUN_GU("동대문구"),
    SEONGDONG_GU("성동구"),
    NOWON_GU("노원구"),
    JUNGNANG_GU("중랑구"),
    GWANGJIN_GU("광진구"),
    GANGDONG_GU("강동구"),
    SONGPA_GU("송파구"),
    GANGNAM_GU("강남구"),
    SEOCHO_GU("서초구"),
    DONGJAK_GU("동작구"),
    GWANAK_GU("관악구"),
    GEUMCHEON_GU("금천구"),
    YEONGDEUNGPO_GU("영등포구"),
    YANGCHEON_GU("양천구"),
    GURO_GU("구로구"),
    GANGSEO_GU("강서구");

    private final String name;

    Location(String name) {
        this.name = name;
    }

    public String getCode() {
        return name();
    }

    public String getName() {
        return name;
    }

    public static Location of(String location) {
        if (Objects.isNull(location)) {
            return null;
        }
        return Arrays.stream(Location.values())
            .filter(v -> v.getCode().equals(location))
            .findFirst()
            .orElseThrow(() -> new CustomException(ErrorCode.LOCATION_NOT_FOUND));
    }
}
