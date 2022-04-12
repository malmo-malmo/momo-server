package com.momo.group.application.dto;

import com.momo.group.domain.Group;
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

    public static GroupCreateResponse of(Group group) {
        return GroupCreateResponse.builder()
            .id(group.getId())
            .imageUrl(group.getImageUrl())
            .build();
    }
}
