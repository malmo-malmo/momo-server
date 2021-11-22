package com.momo.auth.controller.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OAuthLoginRequest {

    @NotBlank(message = "OAuth 공급자는 필수값입니다.")
    private String provider;

    @NotBlank(message = "인가 코드는 필수값입니다.")
    private String authorizationCode;
}
