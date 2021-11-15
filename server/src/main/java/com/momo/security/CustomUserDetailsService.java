package com.momo.security;

import com.momo.user.domain.model.User;
import com.momo.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    User user = userRepository.findById(Long.parseLong(userId))
        .orElseThrow(() ->
            new UsernameNotFoundException("userId를 찾을 수 없습니다. userId : " + userId)
        );
    return UserPrincipal.create(user);
  }
}
