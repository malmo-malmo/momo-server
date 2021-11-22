package com.momo.auth.controller;

import com.momo.auth.controller.dto.OAuthLoginRequest;
import com.momo.auth.controller.dto.OAuthLoginResponse;
import com.momo.auth.service.OAuthService;
import javax.validation.Valid;
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
        OAuthLoginResponse tokenResponse = oAuthService.oauthLogin(oAuthLoginRequest);
        return ResponseEntity.ok(tokenResponse);
    }
}
