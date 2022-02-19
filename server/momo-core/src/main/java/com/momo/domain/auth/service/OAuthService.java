package com.momo.domain.auth.service;

import static com.momo.domain.user.entity.User.REFRESH_TOKEN_RENEWAL_TIME;

import com.momo.domain.auth.dto.OAuthLoginRequest;
import com.momo.domain.auth.dto.OAuthLoginResponse;
import com.momo.domain.auth.dto.RefreshLoginRequest;
import com.momo.domain.auth.provider.OAuthProvider;
import com.momo.domain.auth.provider.OAuthProviderFactory;
import com.momo.domain.auth.provider.TokenProvider;
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

        String refreshToken = tokenProvider.createRefreshToken(loginUser);
        loginUser.getLoginInfo().updateAuthInfo(refreshToken, oAuthLoginRequest.getDeviceCode());

        return new OAuthLoginResponse(tokenProvider.createAccessToken(loginUser), refreshToken);
    }

    public User getUserByOAuthUser(User OAuthUser) {
        return userRepository.findByLoginInfoProviderIdAndLoginInfoProvider(
            OAuthUser.getLoginInfo().getProviderId(),
            OAuthUser.getLoginInfo().getProvider()
        ).orElseGet(() -> userRepository.save(OAuthUser));
    }

    @Transactional(readOnly = true)
    public User findLoginUserByAccessToken(String accessToken) {
        tokenProvider.validateAccessToken(accessToken);
        String userId = tokenProvider.getIdFromAccessToken(accessToken);

        return userRepository.findById(Long.parseLong(userId))
            .orElseThrow(() -> new CustomException(ErrorCode.TOKEN_NOT_FOUND_USER));
    }

    public OAuthLoginResponse refreshLogin(RefreshLoginRequest request) {
        tokenProvider.validateRefreshToken(request.getRefreshToken());
        User loginUser = getUserByRefreshToken(request.getRefreshToken());
        validateDeviceCode(loginUser, request.getDeviceCode());
        validateRefreshTokenRenewalTime(loginUser, request.getRefreshToken());

        return new OAuthLoginResponse(
            tokenProvider.createAccessToken(loginUser), loginUser.getLoginInfo().getRefreshToken()
        );
    }

    private User getUserByRefreshToken(String refreshToken) {
        return userRepository.findByLoginInfoRefreshToken(refreshToken)
            .orElseThrow(() -> new CustomException(ErrorCode.TOKEN_NOT_FOUND_USER));
    }

    private void validateDeviceCode(User loginUser, String deviceCode) {
        if (!loginUser.getLoginInfo().isSameDeviceCode(deviceCode)) {
            throw new CustomException(ErrorCode.INVALID_DEVICE_CODE);
        }
    }

    private void validateRefreshTokenRenewalTime(User loginUser, String refreshToken) {
        long interval = tokenProvider.getRefreshTokenExpHourInterval(refreshToken);
        if (interval <= REFRESH_TOKEN_RENEWAL_TIME) {
            String newRefreshToken = tokenProvider.createRefreshToken(loginUser);
            loginUser.getLoginInfo().updateRefreshToken(newRefreshToken);
        }
    }
}
