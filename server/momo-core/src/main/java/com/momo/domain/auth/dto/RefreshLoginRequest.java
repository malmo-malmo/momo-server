package com.momo.domain.auth.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshLoginRequest {

    @NotBlank(message = "리프레쉬 토큰은 필수값입니다.")
    private String refreshToken;
}
