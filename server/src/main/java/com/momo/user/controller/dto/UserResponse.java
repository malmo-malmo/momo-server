package com.momo.user.controller.dto;

import com.momo.common.dto.EnumResponse;
import com.momo.user.domain.model.User;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String nickname;

    private String image;

    private String city;

    private String district;

    private String university;

    private List<EnumResponse> categories;

    @Builder
    public UserResponse(Long id, String nickname, String image, String city, String district, String university,
        List<EnumResponse> categories) {
        this.id = id;
        this.nickname = nickname;
        this.image = image;
        this.city = city;
        this.district = district;
        this.university = university;
        this.categories = categories;
    }

    public static UserResponse of(User user) {
        return UserResponse.builder()
            .id(user.getId())
            .nickname(user.getNickname())
            .image(user.getImageUrl())
            .city(user.getCity())
            .district(user.getDistrict())
            .university(user.getUniversity())
            .categories(EnumResponse.listFromCategories(user.getFavoriteCategories()))
            .build();
    }
}
