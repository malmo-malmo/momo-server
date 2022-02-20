package com.momo.domain.auth.provider;

import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenProvider {

    @Value("${app.auth.access-token-secret-key}")
    private String accessTokenSecretKey;

    @Value("${app.auth.access-token-expiration-msec}")
    private long accessTokenExpirationMsec;

    @Value("${app.auth.refresh-token-secret-key}")
    private String refreshTokenSecretKey;

    @Value("${app.auth.refresh-token-expiration-msec}")
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

        return Jwts.builder()
            .setSubject(user.getId().toString())
            .setIssuedAt(new Date())
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, refreshTokenSecretKey)
            .compact();
    }

    //테스트용 리프레쉬 토큰 생성
    public String createRefreshTokenForTest(User user, long expirationMsec) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMsec);

        return Jwts.builder()
            .setSubject(user.getId().toString())
            .setIssuedAt(new Date())
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, refreshTokenSecretKey)
            .compact();
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

    public long getRefreshTokenExpHourInterval(String refreshToken) {
        Claims claims = Jwts.parser().setSigningKey(refreshTokenSecretKey).parseClaimsJws(refreshToken).getBody();
        long exp = claims.getExpiration().getTime();
        long now = new Date().getTime();
        return (exp - now) / 3600000;
    }
}
