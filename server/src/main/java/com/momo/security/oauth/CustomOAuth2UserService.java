package com.momo.security.oauth;

import com.momo.security.UserPrincipal;
import com.momo.security.oauth.user.OAuth2UserInfo;
import com.momo.security.oauth.user.OAuth2UserInfoFactory;
import com.momo.user.domain.model.Role;
import com.momo.user.domain.model.SocialProvider;
import com.momo.user.domain.model.User;
import com.momo.user.domain.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  private final UserRepository userRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) {
    OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
    try {
      return processOAuth2User(oAuth2UserRequest, oAuth2User);
    } catch (AuthenticationException e) {
      throw e;
    } catch (Exception e) {
      throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
    }
  }

  private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
    OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(
        oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
    if (StringUtils.isEmpty(oAuth2UserInfo.getId())) {
      throw new OAuth2AuthenticationProcessingException("OAuth2 공급자에서 id를 찾을 수 없습니다.");
    }
    User user = findUserByProviderId(oAuth2UserInfo,
        oAuth2UserRequest.getClientRegistration().getRegistrationId());
    return UserPrincipal.create(user, oAuth2User.getAttributes());
  }

  public User findUserByProviderId(OAuth2UserInfo userInfo, String provider) {
    Optional<User> userOptional = userRepository.findByProviderIdAndProvider(userInfo.getId(),
        SocialProvider.valueOf(provider.toUpperCase()));
    return userOptional.orElseGet(() -> createUser(userInfo, provider));
  }

  public User createUser(OAuth2UserInfo userInfo, String provider) {
    return userRepository.save(
        User.builder()
            .providerId(userInfo.getId())
            .role(Role.ROLE_USER)
            .provider(SocialProvider.valueOf(provider.toUpperCase()))
            .build()
    );
  }
}
