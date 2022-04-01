package com.momo.user.application.dto.request;

import com.momo.district.entity.City;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateRequest {

    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    @Size(min = 1, max = 20, message = "닉네임은 1-20자까지 가능합니다.")
    private String nickname;

    @NotBlank(message = "학교 이름은 필수 입력값입니다.")
    private String university;

    @NotNull(message = "사는 지역은 필수 입력값입니다.")
    private City city;

    @NotBlank(message = "사는 지역은 필수 입력값입니다.")
    private String district;

    @Builder
    public UserUpdateRequest(String nickname, String university, City city, String district) {
        this.nickname = nickname;
        this.university = university;
        this.city = city;
        this.district = district;
    }
}
