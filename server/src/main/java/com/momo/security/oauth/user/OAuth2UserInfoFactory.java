package com.momo.security.oauth.user;

import com.momo.security.oauth.OAuth2AuthenticationProcessingException;
import com.momo.user.domain.model.SocialProvider;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OAuth2UserInfoFactory {

  public static OAuth2UserInfo getOAuth2UserInfo(String registrationId,
      Map<String, Object> attributes) {
    if (registrationId.equalsIgnoreCase(SocialProvider.KAKAO.toString())) {
      return new KakaoOAuth2UserInfo(attributes);
    } else {
      throw new OAuth2AuthenticationProcessingException(registrationId + " 로그인은 지원하지 않습니다.");
    }
  }
}
