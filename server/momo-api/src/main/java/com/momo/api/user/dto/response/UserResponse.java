package com.momo.api.user.dto.response;

import com.momo.domain.common.dto.EnumResponse;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String nickname;
    private String imageUrl;
    private EnumResponse city;
    private String district;
    private String university;
    private List<EnumResponse> categories;

    @Builder
    public UserResponse(
        Long id, String nickname, String imageUrl, EnumResponse city,
        String district, String university, List<EnumResponse> categories
    ) {
        this.id = id;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.city = city;
        this.district = district;
        this.university = university;
        this.categories = categories;
    }
}
