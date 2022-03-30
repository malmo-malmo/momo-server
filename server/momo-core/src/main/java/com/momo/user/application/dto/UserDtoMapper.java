package com.momo.user.application.dto;

import com.momo.user.application.dto.request.UserUpdateRequestDto;
import com.momo.user.application.dto.response.UniversityResponseDto;
import com.momo.user.application.dto.response.UserResponseDto;
import com.momo.user.application.dto.response.UserUpdateResponseDto;
import com.momo.user.domain.Location;
import com.momo.user.domain.User;
import com.momo.user.domain.dto.UniversityDto;
import java.util.List;
import java.util.stream.Collectors;
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

    public UserResponseDto mapToUserResponseDto(User user) {
        return UserResponseDto.builder()
            .id(user.getId())
            .nickname(user.getNickname())
            .imageUrl(user.getImageUrl())
            .city(user.getLocation().getCity())
            .district(user.getLocation().getDistrict())
            .university(user.getLocation().getUniversity())
            .favoriteCategories(user.getFavoriteCategories())
            .build();
    }

    public List<UniversityResponseDto> mapToUniversityResponseDtos(List<UniversityDto> dtos) {
        return dtos.stream()
            .map(dto -> new UniversityResponseDto(dto.getUniversityName()))
            .collect(Collectors.toList());
    }
}
