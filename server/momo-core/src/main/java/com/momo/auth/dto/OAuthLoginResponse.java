package com.momo.auth.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OAuthLoginResponse {

    private String accessTokenType = "Bearer";
    private String accessToken;
    private String refreshToken;

    public OAuthLoginResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
