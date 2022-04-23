package com.momo.user.domain.location;

import com.momo.district.entity.City;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {

    @Enumerated(EnumType.STRING)
    private City city;

    private String district;

    @Builder
    public Location(City city, String district) {
        this.city = city;
        this.district = district;
    }

    public static Location create(Location location) {
        return Location.builder()
            .district(location.getDistrict())
            .city(location.getCity())
            .build();
    }

    public void update(Location location) {
        this.city = location.getCity();
        this.district = location.getDistrict();
    }
}
