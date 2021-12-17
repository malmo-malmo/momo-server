package com.momo.domain.auth;

import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.user.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenProvider {

    @Value("${app.auth.accessTokenSecretKey}")
    private String accessTokenSecretKey;

    @Value("${app.auth.accessTokenExpirationMsec}")
    private long accessTokenExpirationMsec;

    @Value("${app.auth.refreshTokenSecretKey}")
    private String refreshTokenSecretKey;

    @Value("${app.auth.refreshTokenExpirationMsec}")
    private long refreshTokenExpirationMsec;

    public String createAccessToken(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + accessTokenExpirationMsec);

        return Jwts.builder()
            .setSubject(user.getId().toString())
            .setIssuedAt(new Date())
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, accessTokenSecretKey)
            .compact();
    }

    public String createRefreshToken(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + refreshTokenExpirationMsec);

        String refreshToken = Jwts.builder()
            .setSubject(user.getId().toString())
            .setIssuedAt(new Date())
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, refreshTokenSecretKey)
            .compact();

        user.updateRefreshToken(refreshToken);
        return refreshToken;
    }

    public String getIdFromAccessToken(String accessToken) {
        Claims claims = Jwts.parser()
            .setSigningKey(accessTokenSecretKey)
            .parseClaimsJws(accessToken)
            .getBody();

        return claims.getSubject();
    }

    public void validateAccessToken(String accessToken) {
        validateToken(accessToken, accessTokenSecretKey, ErrorCode.INVALID_ACCESS_TOKEN);
    }

    public void validateRefreshToken(String refreshToken) {
        validateToken(refreshToken, refreshTokenSecretKey, ErrorCode.INVALID_REFRESH_TOKEN);
    }

    private void validateToken(String token, String secretKey, ErrorCode errorCode) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomException(errorCode);
        }
    }
}
