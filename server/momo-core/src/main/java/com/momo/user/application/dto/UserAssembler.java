package com.momo.user.application.dto;

import com.momo.common.dto.EnumResponse;
import com.momo.user.application.dto.request.UserUpdateRequest;
import com.momo.user.application.dto.response.UserResponse;
import com.momo.user.application.dto.response.UserUpdateResponse;
import com.momo.user.domain.location.Location;
import com.momo.user.domain.User;

public class UserAssembler {

    public static UserUpdateResponse mapToUserUpdateResponse(User user) {
        return UserUpdateResponse.builder()
            .nickname(user.getNickname())
            .university(user.getLocation().getUniversity())
            .city(EnumResponse.ofCity(user.getLocation().getCity()))
            .district(user.getLocation().getDistrict())
            .build();
    }

    public static UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
            .id(user.getId())
            .nickname(user.getNickname())
            .imageUrl(user.getImageUrl())
            .city(EnumResponse.ofCity(user.getLocation().getCity()))
            .district(user.getLocation().getDistrict())
            .university(user.getLocation().getUniversity())
            .categories(EnumResponse.listOfFavoriteCategories(user.getFavoriteCategories()))
            .build();
    }

    public static Location mapToLocation(UserUpdateRequest request) {
        return Location.builder()
            .city(request.getCity())
            .district(request.getDistrict())
            .university(request.getUniversity())
            .build();
    }
}
