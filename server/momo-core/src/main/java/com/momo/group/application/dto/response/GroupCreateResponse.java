package com.momo.group.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GroupCreateResponse {

    private Long id;
    private String imageUrl;

    @Builder
    public GroupCreateResponse(Long id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }
}
