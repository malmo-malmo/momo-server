package com.momo.auth.service;

import com.momo.auth.TokenProvider;
import com.momo.auth.controller.dto.OAuthLoginRequest;
import com.momo.auth.controller.dto.OAuthLoginResponse;
import com.momo.auth.domain.OAuthProvider;
import com.momo.auth.domain.OAuthProviderFactory;
import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.user.domain.model.User;
import com.momo.user.domain.repository.UserRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OAuthService {

    private final UserRepository userRepository;

    private final OAuthProviderFactory oAuthProviderFactory;

    private final TokenProvider tokenProvider;

    public OAuthLoginResponse oauthLogin(OAuthLoginRequest oAuthLoginRequest) {
        OAuthProvider oAuthProvider = oAuthProviderFactory.getOAuthProvider(oAuthLoginRequest.getProvider());
        User user = oAuthProvider.requestOAuthLogin(oAuthLoginRequest.getAuthorizationCode());
        User loginUser = userRepository.findByProviderIdAndProvider(user.getProviderId(), user.getProvider())
            .orElseGet(() -> userRepository.save(user));
        return new OAuthLoginResponse(tokenProvider.createToken(loginUser));
    }

    public User findLoginUserByAccessToken(String accessToken) {
        if (Objects.isNull(accessToken) || tokenProvider.isInvalidToken(accessToken)) {
            throw new CustomException(ErrorCode.INVALID_ACCESS_TOKEN);
        }
        String userId = tokenProvider.getIdFromToken(accessToken);
        return userRepository.findById(Long.parseLong(userId))
            .orElseThrow(() -> new CustomException(ErrorCode.ACCESS_TOKEN_NOT_FOUND_USER));
    }
}
