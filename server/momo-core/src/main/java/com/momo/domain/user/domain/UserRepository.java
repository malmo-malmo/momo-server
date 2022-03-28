package com.momo.domain.user.domain;

import com.momo.domain.user.domain.SocialProvider;
import com.momo.domain.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findBySocialLoginProviderIdAndSocialLoginProvider(String providerId, SocialProvider provider);

    boolean existsByNickname(String nickname);
}
