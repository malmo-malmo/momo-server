package com.momo.user.dto;

import com.momo.district.entity.City;
import com.momo.user.entity.Location;
import com.momo.user.entity.User;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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

    private MultipartFile image;

    @Builder
    public UserUpdateRequest(String nickname, String university, City city, String district, MultipartFile image) {
        this.nickname = nickname;
        this.university = university;
        this.city = city;
        this.district = district;
        this.image = image;
    }

    public User toUser() {
        return User.builder()
            .nickname(nickname)
            .build();
    }

    public Location toLocation() {
        return Location.builder()
            .university(university)
            .district(district)
            .city(city)
            .build();
    }
}
