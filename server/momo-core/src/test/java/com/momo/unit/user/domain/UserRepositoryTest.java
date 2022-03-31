package com.momo.unit.user.domain;

import static com.momo.UserFixture.getUser;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.RepositoryTest;
import com.momo.user.domain.model.User;
import com.momo.user.domain.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("유저 레포지토리 테스트")
public class UserRepositoryTest extends RepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void before() {
        user = userRepository.save(getUser());
    }

    @Test
    @DisplayName("유저를 저장한다")
    void saveUserTest() {
        User user = userRepository.findAll().get(0);
        Assertions.assertAll(
            () -> assertThat(user.getId()).isNotNull(),
            () -> assertThat(user.getSocialLogin().getProvider()).isEqualTo(user.getSocialLogin().getProvider()),
            () -> assertThat(user.getSocialLogin().getProviderId()).isEqualTo(user.getSocialLogin().getProviderId()),
            () -> assertThat(user.getNickname()).isEqualTo(user.getNickname()),
            () -> assertThat(user.getImageUrl()).isEqualTo(user.getImageUrl()),
            () -> assertThat(user.getLocation().getCity()).isEqualTo(user.getLocation().getCity()),
            () -> assertThat(user.getLocation().getDistrict()).isEqualTo(user.getLocation().getDistrict()),
            () -> assertThat(user.getLocation().getUniversity()).isEqualTo(user.getLocation().getUniversity())
        );
    }

    @Test
    @DisplayName("공급자 ID와 공급자 이름으로 유저를 조회한다")
    void findUserByProviderIdAndProviderTest() {
        User actual = userRepository.findBySocialLoginProviderIdAndSocialLoginProvider(
            user.getSocialLogin().getProviderId(),
            user.getSocialLogin().getProvider()
        ).get();

        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.getId()).isNotNull(),
            () -> assertThat(actual.getSocialLogin().getProvider()).isEqualTo(user.getSocialLogin().getProvider()),
            () -> assertThat(actual.getSocialLogin().getProviderId()).isEqualTo(user.getSocialLogin().getProviderId()),
            () -> assertThat(actual.getNickname()).isEqualTo(user.getNickname()),
            () -> assertThat(actual.getImageUrl()).isEqualTo(user.getImageUrl()),
            () -> assertThat(actual.getLocation().getCity()).isEqualTo(user.getLocation().getCity()),
            () -> assertThat(actual.getLocation().getDistrict()).isEqualTo(user.getLocation().getDistrict()),
            () -> assertThat(actual.getLocation().getUniversity()).isEqualTo(user.getLocation().getUniversity())
        );
    }

    @Test
    @DisplayName("해당 닉네임을 가진 유저가 존재하는지 확인한다")
    void existsByNicknameTest() {
        boolean actual = userRepository.existsByNickname(user.getNickname());
        assertThat(actual).isTrue();
    }
}
