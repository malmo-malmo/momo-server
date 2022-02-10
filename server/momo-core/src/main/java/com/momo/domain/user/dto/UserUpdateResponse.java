package com.momo.domain.user.dto;

import com.momo.domain.common.dto.EnumResponse;
import com.momo.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateResponse {

    private String nickname;
    private String university;
    private EnumResponse city;
    private String district;
    private String imageUrl;

    @Builder
    public UserUpdateResponse(String nickname, String university, EnumResponse city, String district, String imageUrl) {
        this.nickname = nickname;
        this.university = university;
        this.city = city;
        this.district = district;
        this.imageUrl = imageUrl;
    }

    public static UserUpdateResponse of(User user) {
        return UserUpdateResponse.builder()
            .nickname(user.getNickname())
            .university(user.getLocation().getUniversity())
            .city(EnumResponse.ofCity(user.getLocation().getCity()))
            .district(user.getLocation().getDistrict())
            .imageUrl(user.getImageUrl())
            .build();
    }
}
