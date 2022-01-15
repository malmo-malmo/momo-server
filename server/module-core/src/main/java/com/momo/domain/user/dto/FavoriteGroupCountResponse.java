package com.momo.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteGroupCountResponse {

    private Long count;

    public static FavoriteGroupCountResponse of(Long count) {
        return new FavoriteGroupCountResponse(count);
    }
}
