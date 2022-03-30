package com.momo.user.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UniversityResponseDto {

    private String name;

    public UniversityResponseDto(String name) {
        this.name = name;
    }
}
