package com.momo.domain.user.repository;

import com.momo.domain.user.entity.SocialProvider;
import com.momo.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLoginInfoRefreshToken(String refreshToken);

    Optional<User> findByLoginInfoProviderIdAndLoginInfoProvider(String providerId, SocialProvider provider);

    boolean existsByNickname(String nickname);
}
