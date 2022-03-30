package com.momo.user.application.dto.response;

import com.momo.district.entity.City;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserUpdateResponseDto {

    private String nickname;
    private String university;
    private City city;
    private String district;

    @Builder
    public UserUpdateResponseDto(String nickname, String university, City city, String district) {
        this.nickname = nickname;
        this.university = university;
        this.city = city;
        this.district = district;
    }
}
