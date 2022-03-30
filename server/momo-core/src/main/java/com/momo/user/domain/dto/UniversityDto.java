package com.momo.user.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UniversityDto {

    private String universityName;

    public UniversityDto(String universityName) {
        this.universityName = universityName;
    }
}
