package com.momo.api.auth;

import javax.validation.Valid;

import com.momo.domain.auth.dto.OAuthLoginRequest;
import com.momo.domain.auth.dto.OAuthLoginResponse;
import com.momo.domain.auth.dto.RefreshLoginRequest;
import com.momo.domain.auth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService oAuthService;

    @PostMapping("/oauth/login")
    public ResponseEntity<OAuthLoginResponse> oauthLogin(@Valid @RequestBody OAuthLoginRequest oAuthLoginRequest) {
        OAuthLoginResponse response = oAuthService.oauthLogin(oAuthLoginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/oauth/login/refresh")
    public ResponseEntity<OAuthLoginResponse> refreshLogin(
        @Valid @RequestBody RefreshLoginRequest refreshLoginRequest) {
        OAuthLoginResponse response = oAuthService.refreshLogin(refreshLoginRequest);
        return ResponseEntity.ok(response);
    }
}
