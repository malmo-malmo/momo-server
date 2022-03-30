package com.momo.user.dto;

import com.momo.common.dto.EnumResponse;
import com.momo.user.application.dto.request.UserUpdateRequestDto;
import com.momo.user.application.dto.response.UserResponseDto;
import com.momo.user.application.dto.response.UserUpdateResponseDto;
import com.momo.user.dto.request.UserUpdateRequest;
import com.momo.user.dto.response.UserResponse;
import com.momo.user.dto.response.UserUpdateResponse;

public class UserAssembler {

    public static UserUpdateRequestDto mapToUserUpdateRequestDto(UserUpdateRequest request) {
        return UserUpdateRequestDto.builder()
            .nickname(request.getNickname())
            .university(request.getUniversity())
            .city(request.getCity())
            .district(request.getDistrict())
            .build();
    }

    public static UserUpdateResponse mapToUserUpdateResponse(UserUpdateResponseDto dto) {
        return UserUpdateResponse.builder()
            .nickname(dto.getNickname())
            .university(dto.getUniversity())
            .city(EnumResponse.ofCity(dto.getCity()))
            .district(dto.getDistrict())
            .build();
    }

    public static UserResponse mapToUserResponse(UserResponseDto dto) {
        return UserResponse.builder()
            .id(dto.getId())
            .nickname(dto.getNickname())
            .imageUrl(dto.getImageUrl())
            .city(EnumResponse.ofCity(dto.getCity()))
            .district(dto.getDistrict())
            .university(dto.getUniversity())
            .categories(EnumResponse.listOfFavoriteCategories(dto.getFavoriteCategories()))
            .build();
    }
}
