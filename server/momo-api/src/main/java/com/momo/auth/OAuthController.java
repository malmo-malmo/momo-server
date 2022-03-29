package com.momo.auth;

import com.momo.auth.dto.OAuthLoginRequest;
import com.momo.auth.dto.OAuthLoginResponse;
import com.momo.auth.dto.RefreshLoginRequest;
import com.momo.auth.service.OAuthService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth")
public class OAuthController {

    private final OAuthService oAuthService;

    @PostMapping("/login")
    public ResponseEntity<OAuthLoginResponse> oauthLogin(@Valid @RequestBody OAuthLoginRequest request) {
        OAuthLoginResponse response = oAuthService.oauthLogin(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login/refresh")
    public ResponseEntity<OAuthLoginResponse> refreshLogin(@Valid @RequestBody RefreshLoginRequest request) {
        OAuthLoginResponse response = oAuthService.refreshLogin(request);
        return ResponseEntity.ok(response);
    }
}
