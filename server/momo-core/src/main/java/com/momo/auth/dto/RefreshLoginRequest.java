package com.momo.auth.dto;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RefreshLoginRequest {

    @NotBlank(message = "리프레쉬 토큰은 필수값입니다.")
    private String refreshToken;

    @NotBlank(message = "기기 고유번호는 필수값입니다.")
    private String deviceCode;

    @Builder
    public RefreshLoginRequest(String refreshToken, String deviceCode) {
        this.refreshToken = refreshToken;
        this.deviceCode = deviceCode;
    }
}
