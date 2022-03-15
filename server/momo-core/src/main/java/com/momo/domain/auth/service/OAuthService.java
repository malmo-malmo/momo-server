package com.momo.domain.auth.service;

import com.momo.domain.auth.dto.OAuthLoginRequest;
import com.momo.domain.auth.dto.OAuthLoginResponse;
import com.momo.domain.auth.dto.RefreshLoginRequest;
import com.momo.domain.auth.entity.AccessTokenReissuance;
import com.momo.domain.auth.provider.OAuthProvider;
import com.momo.domain.auth.provider.OAuthProviderFactory;
import com.momo.domain.auth.provider.TokenProvider;
import com.momo.domain.auth.repository.AccessTokenReissuanceRepository;
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
    private final AccessTokenReissuanceRepository accessTokenReissuanceRepository;
    private final OAuthProviderFactory oAuthProviderFactory;
    private final TokenProvider tokenProvider;

    public OAuthLoginResponse oauthLogin(OAuthLoginRequest oAuthLoginRequest) {
        OAuthProvider oAuthProvider = oAuthProviderFactory.getOAuthProvider(oAuthLoginRequest.getProvider());
        User OAuthUser = oAuthProvider.requestOAuthLogin(oAuthLoginRequest.getAuthorizationCode());
        User loginUser = getUserByOAuthUser(OAuthUser);

        String refreshToken = tokenProvider.createRefreshToken(loginUser.getId());
        String accessToken = tokenProvider.createAccessToken(loginUser.getId());

        saveAccessTokenReissuance(refreshToken, loginUser.getId(), oAuthLoginRequest.getDeviceCode());

        return new OAuthLoginResponse(accessToken, refreshToken);
    }

    private User getUserByOAuthUser(User OAuthUser) {
        return userRepository.findBySocialLoginProviderIdAndSocialLoginProvider(
            OAuthUser.getSocialLogin().getProviderId(),
            OAuthUser.getSocialLogin().getProvider()
        ).orElseGet(() -> userRepository.save(OAuthUser));
    }

    @Transactional(readOnly = true)
    public User findLoginUserByAccessToken(String accessToken) {
        String userId = tokenProvider.getIdFromAccessToken(accessToken);

        return userRepository.findById(Long.parseLong(userId))
            .orElseThrow(() -> new CustomException(ErrorCode.TOKEN_NOT_FOUND_USER));
    }

    public OAuthLoginResponse refreshLogin(RefreshLoginRequest request) {
        tokenProvider.validateRefreshToken(request.getRefreshToken());

        AccessTokenReissuance reissuance = getAccessTokenReissuanceByRefreshToken(request.getRefreshToken());
        String accessToken = tokenProvider.createAccessToken(reissuance.getUserId());
        String refreshToken = reissuance.getRefreshToken();

        validateDeviceCode(reissuance, request.getDeviceCode());

        if (tokenProvider.isOverRefreshTokenRenewalHour(refreshToken)) {
            return new OAuthLoginResponse(accessToken, refreshToken);
        }

        refreshToken = tokenProvider.createRefreshToken(reissuance.getUserId());
        accessTokenReissuanceRepository.delete(reissuance);
        saveAccessTokenReissuance(refreshToken, reissuance.getUserId(), reissuance.getDeviceCode());

        return new OAuthLoginResponse(accessToken, refreshToken);
    }

    private AccessTokenReissuance getAccessTokenReissuanceByRefreshToken(String refreshToken) {
        return accessTokenReissuanceRepository.findById(refreshToken)
            .orElseThrow(() -> new CustomException(ErrorCode.TOKEN_NOT_FOUND_USER));
    }

    private void validateDeviceCode(AccessTokenReissuance tokenReissuance, String deviceCode) {
        if (!tokenReissuance.isSameDeviceCode(deviceCode)) {
            throw new CustomException(ErrorCode.INVALID_DEVICE_CODE);
        }
    }

    private void saveAccessTokenReissuance(String refreshToken, Long userId, String deviceCode) {
        accessTokenReissuanceRepository.save(
            AccessTokenReissuance.create(refreshToken, userId, deviceCode)
        );
    }
}
