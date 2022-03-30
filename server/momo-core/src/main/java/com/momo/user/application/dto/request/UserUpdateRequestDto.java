package com.momo.user.application.dto.request;

import com.momo.district.entity.City;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserUpdateRequestDto {

    private String nickname;
    private String university;
    private City city;
    private String district;

    @Builder
    public UserUpdateRequestDto(String nickname, String university, City city, String district) {
        this.nickname = nickname;
        this.university = university;
        this.city = city;
        this.district = district;
    }
}
