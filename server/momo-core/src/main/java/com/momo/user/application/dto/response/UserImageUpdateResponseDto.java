package com.momo.user.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserImageUpdateResponseDto {

    private String imageUrl;

    public UserImageUpdateResponseDto(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
