package com.momo.domain.auth.service;

import com.momo.domain.auth.provider.TokenProvider;
import com.momo.domain.auth.provider.OAuthProvider;
import com.momo.domain.auth.provider.OAuthProviderFactory;
import com.momo.domain.auth.dto.OAuthLoginRequest;
import com.momo.domain.auth.dto.OAuthLoginResponse;
import com.momo.domain.auth.dto.RefreshLoginRequest;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.user.entity.User;
import com.momo.domain.user.repository.UserRepository;
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
        User OAuthUser = oAuthProvider.requestOAuthLogin(oAuthLoginRequest.getAuthorizationCode());
        User loginUser = getUserByOAuthUser(OAuthUser);
        return new OAuthLoginResponse(
            tokenProvider.createAccessToken(loginUser), tokenProvider.createRefreshToken(loginUser)
        );
    }

    public User getUserByOAuthUser(User OAuthUser) {
        return userRepository.findByProviderIdAndProvider(OAuthUser.getProviderId(), OAuthUser.getProvider())
            .orElseGet(() -> userRepository.save(OAuthUser));
    }

    public User findLoginUserByAccessToken(String accessToken) {
        tokenProvider.validateAccessToken(accessToken);
        String userId = tokenProvider.getIdFromAccessToken(accessToken);
        return userRepository.findById(Long.parseLong(userId))
            .orElseThrow(() -> new CustomException(ErrorCode.TOKEN_NOT_FOUND_USER));
    }

    public OAuthLoginResponse refreshLogin(RefreshLoginRequest refreshLoginRequest) {
        tokenProvider.validateRefreshToken(refreshLoginRequest.getRefreshToken());
        User user = userRepository.findByRefreshToken(refreshLoginRequest.getRefreshToken())
            .orElseThrow(() -> new CustomException(ErrorCode.TOKEN_NOT_FOUND_USER));
        return new OAuthLoginResponse(tokenProvider.createAccessToken(user), tokenProvider.createRefreshToken(user));
    }
}
