package com.momo.user.domain.repository;

import com.momo.user.domain.model.SocialProvider;
import com.momo.user.domain.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByRefreshToken(String refreshToken);

    Optional<User> findByProviderIdAndProvider(String providerId, SocialProvider provider);

    boolean existsByNickname(String nickname);
}
