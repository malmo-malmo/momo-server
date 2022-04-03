package com.momo.auth.dto;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OAuthLoginRequest {

    @NotBlank(message = "OAuth 공급자는 필수값입니다.")
    private String provider;

    @NotBlank(message = "인가 코드는 필수값입니다.")
    private String authorizationCode;

    @NotBlank(message = "기기 고유번호는 필수값입니다.")
    private String deviceCode;

    @Builder
    public OAuthLoginRequest(String provider, String authorizationCode, String deviceCode) {
        this.provider = provider;
        this.authorizationCode = authorizationCode;
        this.deviceCode = deviceCode;
    }
}
