package com.momo.user.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UniversityResponseDto {

    private String universityName;

    public UniversityResponseDto(String universityName) {
        this.universityName = universityName;
    }
}
