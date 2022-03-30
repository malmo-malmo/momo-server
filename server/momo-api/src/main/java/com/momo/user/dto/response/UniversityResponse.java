package com.momo.user.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UniversityResponse {

    private String universityName;

    public UniversityResponse(String universityName) {
        this.universityName = universityName;
    }
}
