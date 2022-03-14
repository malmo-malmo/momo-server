package com.momo.domain.user.repository;

import com.momo.domain.user.entity.SocialProvider;
import com.momo.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findBySocialLoginProviderIdAndSocialLoginProvider(String providerId, SocialProvider provider);

    boolean existsByNickname(String nickname);
}
