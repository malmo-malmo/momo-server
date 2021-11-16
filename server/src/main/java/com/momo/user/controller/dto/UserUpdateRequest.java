package com.momo.user.controller.dto;

import com.momo.user.domain.model.Location;
import com.momo.user.domain.model.User;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "지역은 필수 입력값입니다.")
    private String location;

    @Builder
    public UserUpdateRequest(String nickname, String university, String location) {
        this.nickname = nickname;
        this.university = university;
        this.location = location;
    }

    public User toEntity() {
        return User.builder()
            .nickname(nickname)
            .university(university)
            .location(Location.of(location))
            .build();
    }
}
