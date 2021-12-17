package com.momo.domain.user.domain.repository;

import com.momo.domain.user.domain.model.SocialProvider;
import com.momo.domain.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByRefreshToken(String refreshToken);

    Optional<User> findByProviderIdAndProvider(String providerId, SocialProvider provider);

    boolean existsByNickname(String nickname);
}
