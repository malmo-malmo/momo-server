package com.momo.common;

import static com.momo.common.FixtureComponents.CITY;
import static com.momo.common.FixtureComponents.DISTRICT;
import static com.momo.common.FixtureComponents.UNIVERSITY;

import com.momo.user.domain.location.Location;

public class LocationFixture {

    public static Location getLocation() {
        return Location.builder()
            .city(CITY)
            .district(DISTRICT)
            .build();
    }

    public static Location getLocation(String district) {
        return Location.builder()
            .city(CITY)
            .district(district)
            .build();
    }
}
