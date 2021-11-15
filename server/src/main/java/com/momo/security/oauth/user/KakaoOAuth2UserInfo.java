package com.momo.security.oauth.user;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

  public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
    super(attributes);
  }

  @Override
  public String getId() {
    return attributes.get("id").toString();
  }

  @Override
  public String getName() {
    Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
    if (properties == null) {
      return null;
    }
    return (String) properties.get("nickname");
  }

  @Override
  public String getImageUrl() {
    Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
    if (properties == null) {
      return null;
    }
    return (String) properties.get("thumbnail_image");
  }
}
