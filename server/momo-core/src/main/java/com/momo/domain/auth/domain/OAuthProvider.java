package com.momo.domain.auth.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.user.domain.User;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

public abstract class OAuthProvider {

    protected RestTemplate restTemplate;
    protected String clientId;
    protected String clientSecret;
    protected String tokenUri;
    protected String userInfoUri;

    public User requestOAuthLogin(String authorizationCode) {
        String accessToken = requestAccessToken(authorizationCode);
        return requestUserInformation(accessToken);
    }

    private String requestAccessToken(String authorizationCode) {
        try {
            ResponseEntity<String> tokenResponse = restTemplate
                .exchange(tokenUri, POST, createAccessTokenRequest(authorizationCode), String.class);
            return parseAccessToken(tokenResponse);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INVALID_OAUTH_AUTHORIZATION_CODE);
        }
    }

    private HttpEntity<MultiValueMap<String, String>> createAccessTokenRequest(String authorizationCode) {
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "authorization_code");
        requestBody.add("code", authorizationCode);
        requestBody.add("client_id", clientId);
        requestBody.add("client_secret", clientSecret);

        HttpHeaders requestHeader = new HttpHeaders();
        requestHeader.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return new HttpEntity<>(requestBody, requestHeader);
    }

    private User requestUserInformation(String accessToken) {
        try {
            ResponseEntity<String> tokenResponse = restTemplate
                .exchange(userInfoUri, GET, createUserInformationRequest(accessToken), String.class);
            return parseSocialLoginUser(tokenResponse);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INVALID_OAUTH_ACCESS_TOKEN);
        }
    }

    private HttpEntity<MultiValueMap<String, String>> createUserInformationRequest(String accessToken) {
        HttpHeaders requestHeader = new HttpHeaders();
        requestHeader.setBearerAuth(accessToken);
        requestHeader.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        requestHeader.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
        return new HttpEntity<>(requestHeader);
    }

    abstract protected String parseAccessToken(ResponseEntity<String> response);

    abstract protected User parseSocialLoginUser(ResponseEntity<String> response) throws JsonProcessingException, ParseException;
}
