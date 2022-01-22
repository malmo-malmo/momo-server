package com.momo.domain.favorite.dto;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FavoriteGroupCreateRequest {

    @NotNull(message = "모임 ID는 필수값입니다.")
    private Long groupId;

    @Builder
    public FavoriteGroupCreateRequest(Long groupId) {
        this.groupId = groupId;
    }
}
