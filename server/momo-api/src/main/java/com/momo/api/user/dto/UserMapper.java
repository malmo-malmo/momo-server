package com.momo.api.user.dto;

import com.momo.api.user.dto.request.UserUpdateRequest;
import com.momo.api.user.dto.response.UserUpdateResponse;
import com.momo.domain.common.dto.EnumResponse;
import com.momo.domain.user.application.dto.request.UserUpdateRequestDto;
import com.momo.domain.user.application.dto.response.UserUpdateResponseDto;
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
}
