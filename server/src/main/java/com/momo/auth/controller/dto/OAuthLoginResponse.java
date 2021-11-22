package com.momo.auth.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OAuthLoginResponse {

    private String accessTokenType = "Bearer";
    private String accessToken;

    public OAuthLoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
