package com.momo.api.user.dto.response;

import com.momo.domain.common.dto.EnumResponse;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateResponse {

    private String nickname;
    private String university;
    private EnumResponse city;
    private String district;

    @Builder
    public UserUpdateResponse(String nickname, String university, EnumResponse city, String district) {
        this.nickname = nickname;
        this.university = university;
        this.city = city;
        this.district = district;
    }
}
