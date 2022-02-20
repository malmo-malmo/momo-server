package com.momo.domain.user.entity;

import com.momo.domain.district.entity.City;
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

    private String university;

    @Builder
    public Location(City city, String district, String university) {
        this.city = city;
        this.district = district;
        this.university = university;
    }

    public static Location fromEmptyUniversity(Location location) {
        return Location.builder()
            .university(null)
            .district(location.getDistrict())
            .city(location.getCity())
            .build();
    }

    public static Location fromEmptyUniversity(City city, String district) {
        return Location.builder()
            .university(null)
            .district(district)
            .city(city)
            .build();
    }

    public static Location fromUniversity(String university, Location location) {
        return Location.builder()
            .university(university)
            .district(location.getDistrict())
            .city(location.getCity())
            .build();
    }

    public void update(Location location) {
        this.city = location.getCity();
        this.district = location.getDistrict();
        this.university = location.getUniversity();
    }
}
