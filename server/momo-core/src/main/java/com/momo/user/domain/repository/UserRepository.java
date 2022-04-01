package com.momo.user.domain.repository;

import com.momo.user.domain.model.User;
import com.momo.user.domain.model.SocialProvider;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findBySocialLoginProviderIdAndSocialLoginProvider(String providerId, SocialProvider provider);

    boolean existsByNickname(String nickname);
}
