package com.momo.user.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserImageUpdateResponse {

    private String imageUrl;

    public UserImageUpdateResponse(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
