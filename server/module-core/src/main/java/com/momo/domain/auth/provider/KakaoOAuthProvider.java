package com.momo.domain.auth.provider;

import com.momo.domain.user.entity.SocialProvider;
import com.momo.domain.user.entity.User;
import lombok.Getter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Getter
@Component
public class KakaoOAuthProvider extends OAuthProvider {

    public KakaoOAuthProvider(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    @Value("${app.oauth2.kakao.client-id}")
    private void setClientId(String clientId) {
        super.clientId = clientId;
    }

    @Value("${app.oauth2.kakao.client-secret}")
    private void setClientSecret(String clientSecret) {
        super.clientSecret = clientSecret;
    }

    @Value("${app.oauth2.kakao.token-uri}")
    private void setTokenUri(String tokenUri) {
        super.tokenUri = tokenUri;
    }

    @Value("${app.oauth2.kakao.user-info-uri}")
    private void setUserInfoUri(String userInfoUri) {
        super.userInfoUri = userInfoUri;
    }

    @Override
    protected String parseAccessToken(ResponseEntity<String> response) {
        JSONObject jsonObject = new JSONObject(response.getBody());
        return jsonObject.getString("access_token");
    }

    @Override
    protected User parseSocialLoginUser(ResponseEntity<String> response) {
        JSONObject jsonObject = new JSONObject(response.getBody());
        return User.createSocialLoginUser(String.valueOf(jsonObject.get("id")), SocialProvider.KAKAO);
    }
}
