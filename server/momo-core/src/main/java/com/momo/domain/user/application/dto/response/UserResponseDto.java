package com.momo.domain.user.application.dto.response;

import com.momo.domain.district.entity.City;
import com.momo.domain.favorite.entity.FavoriteCategories;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {

    private Long id;
    private String nickname;
    private String imageUrl;
    private City city;
    private String district;
    private String university;
    private FavoriteCategories favoriteCategories;

    @Builder
    public UserResponseDto(
        Long id, String nickname, String imageUrl, City city,
        String district, String university, FavoriteCategories favoriteCategories
    ) {
        this.id = id;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.city = city;
        this.district = district;
        this.university = university;
        this.favoriteCategories = favoriteCategories;
    }
}
