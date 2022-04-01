package com.momo.auth.service;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.auth.domain.AccessTokenReissuance;
import com.momo.auth.domain.OAuthProvider;
import com.momo.auth.domain.OAuthProviderFactory;
import com.momo.auth.dto.OAuthLoginRequest;
import com.momo.auth.dto.OAuthLoginResponse;
import com.momo.auth.dto.RefreshLoginRequest;
import com.momo.auth.infra.TokenProvider;
import com.momo.auth.infra.TokenReissuanceDao;
import com.momo.user.domain.model.User;
import com.momo.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OAuthService {

    private final UserRepository userRepository;
    private final TokenReissuanceDao tokenReissuanceDao;
    private final OAuthProviderFactory oAuthProviderFactory;
    private final TokenProvider tokenProvider;

    public OAuthLoginResponse oauthLogin(OAuthLoginRequest oAuthLoginRequest) {
        OAuthProvider oAuthProvider = oAuthProviderFactory.getOAuthProvider(oAuthLoginRequest.getProvider());
        User OAuthUser = oAuthProvider.requestOAuthLogin(oAuthLoginRequest.getAuthorizationCode());
        User loginUser = getUserByOAuthUser(OAuthUser);

        String refreshToken = tokenProvider.createRefreshToken(loginUser.getId());
        String accessToken = tokenProvider.createAccessToken(loginUser.getId());

        tokenReissuanceDao.insert(refreshToken, loginUser.getId(), oAuthLoginRequest.getDeviceCode());

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

        validateDeviceCode(reissuance, request.getDeviceCode());
        if (tokenProvider.isOverRefreshTokenRenewalHour(request.getRefreshToken())) {
            return new OAuthLoginResponse(accessToken, request.getRefreshToken());
        }

        String refreshToken = tokenProvider.createRefreshToken(reissuance.getUserId());
        tokenReissuanceDao.insert(refreshToken, reissuance.getUserId(), reissuance.getDeviceCode());

        return new OAuthLoginResponse(accessToken, refreshToken);
    }

    private AccessTokenReissuance getAccessTokenReissuanceByRefreshToken(String refreshToken) {
        return tokenReissuanceDao.findByRefreshToken(refreshToken)
            .orElseThrow(() -> new CustomException(ErrorCode.TOKEN_NOT_FOUND_USER));
    }

    private void validateDeviceCode(AccessTokenReissuance tokenReissuance, String deviceCode) {
        if (!tokenReissuance.isSameDeviceCode(deviceCode)) {
            throw new CustomException(ErrorCode.INVALID_DEVICE_CODE);
        }
    }
}
