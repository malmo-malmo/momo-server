package com.momo.domain.user.repository;

import com.momo.domain.user.entity.SocialProvider;
import com.momo.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByRefreshToken(String refreshToken);

    Optional<User> findByProviderIdAndProvider(String providerId, SocialProvider provider);

    boolean existsByNickname(String nickname);
}
