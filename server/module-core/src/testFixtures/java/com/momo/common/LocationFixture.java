package com.momo.common;

import static com.momo.common.FixtureComponents.CITY;
import static com.momo.common.FixtureComponents.DISTRICT;
import static com.momo.common.FixtureComponents.UNIVERSITY;

import com.momo.domain.user.entity.Location;

public class LocationFixture {

    public static Location getLocation() {
        return Location.builder()
            .city(CITY)
            .district(DISTRICT)
            .university(UNIVERSITY)
            .build();
    }

    public static Location getLocation(String district, String university) {
        return Location.builder()
            .city(CITY)
            .district(district)
            .university(university)
            .build();
    }
}
