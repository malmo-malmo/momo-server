package com.momo.group.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GroupImageUpdateResponse {

    private String imageUrl;

    public GroupImageUpdateResponse(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
