package com.momo.favorite.dto;

import com.momo.group.application.dto.response.GroupCardResponse;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FavoriteGroupCardResponse {

    private Long id;
    private GroupCardResponse groupCardResponse;

    @Builder
    @QueryProjection
    public FavoriteGroupCardResponse(Long id, GroupCardResponse groupCardResponse) {
        this.id = id;
        this.groupCardResponse = groupCardResponse;
    }
}
