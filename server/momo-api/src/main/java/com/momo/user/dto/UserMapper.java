package com.momo.user.dto;

import com.momo.common.dto.EnumResponse;
import com.momo.user.application.dto.request.UserUpdateRequestDto;
import com.momo.user.application.dto.response.UniversityResponseDto;
import com.momo.user.application.dto.response.UserResponseDto;
import com.momo.user.application.dto.response.UserUpdateResponseDto;
import com.momo.user.dto.request.UserUpdateRequest;
import com.momo.user.dto.response.UniversityResponse;
import com.momo.user.dto.response.UserResponse;
import com.momo.user.dto.response.UserUpdateResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserUpdateRequestDto mapToUserUpdateRequestDto(UserUpdateRequest request) {
        return UserUpdateRequestDto.builder()
            .nickname(request.getNickname())
            .university(request.getUniversity())
            .city(request.getCity())
            .district(request.getDistrict())
            .build();
    }

    public UserUpdateResponse mapToUserUpdateResponse(UserUpdateResponseDto dto) {
        return UserUpdateResponse.builder()
            .nickname(dto.getNickname())
            .university(dto.getUniversity())
            .city(EnumResponse.ofCity(dto.getCity()))
            .district(dto.getDistrict())
            .build();
    }

    public UserResponse mapToUserResponse(UserResponseDto dto) {
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
