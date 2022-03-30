package com.momo.user.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UniversityResponse {

    private String name;

    public UniversityResponse(String name) {
        this.name = name;
    }
}
