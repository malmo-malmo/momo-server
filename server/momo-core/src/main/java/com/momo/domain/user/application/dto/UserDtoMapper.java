package com.momo.domain.user.application.dto;

import com.momo.domain.user.application.dto.request.UserUpdateRequestDto;
import com.momo.domain.user.application.dto.response.UserUpdateResponseDto;
import com.momo.domain.user.domain.Location;
import com.momo.domain.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    public Location mapToLocation(UserUpdateRequestDto dto) {
        return Location.builder()
            .city(dto.getCity())
            .district(dto.getDistrict())
            .university(dto.getUniversity())
            .build();
    }

    public UserUpdateResponseDto mapToUserUpdateResponseDto(User user) {
        return UserUpdateResponseDto.builder()
            .nickname(user.getNickname())
            .city(user.getLocation().getCity())
            .district(user.getLocation().getDistrict())
            .university(user.getLocation().getUniversity())
            .build();
    }
}
