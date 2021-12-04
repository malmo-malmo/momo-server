package com.momo.auth.domain;

import com.momo.user.domain.model.SocialProvider;
import com.momo.user.domain.model.User;
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

    @Value("${app.oauth2.kakao.clientId}")
    private void setClientId(String clientId) {
        super.clientId = clientId;
    }

    @Value("${app.oauth2.kakao.clientSecret}")
    private void setClientSecret(String clientSecret) {
        super.clientSecret = clientSecret;
    }

    @Value("${app.oauth2.kakao.tokenUri}")
    private void setTokenUri(String tokenUri) {
        super.tokenUri = tokenUri;
    }

    @Value("${app.oauth2.kakao.userInfoUri}")
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
        return User.builder()
            .providerId(String.valueOf(jsonObject.get("id")))
            .provider(SocialProvider.KAKAO)
            .build();
    }
}
