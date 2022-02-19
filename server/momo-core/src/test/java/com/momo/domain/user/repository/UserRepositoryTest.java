package com.momo.domain.user.repository;

import static com.momo.UserFixture.getUser;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.RepositoryTest;
import com.momo.domain.user.entity.User;
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
    void 유저를_저장한다() {
        User user = userRepository.findAll().get(0);
        Assertions.assertAll(
            () -> assertThat(user.getId()).isNotNull(),
            () -> assertThat(user.getLoginInfo().getProvider()).isEqualTo(user.getLoginInfo().getProvider()),
            () -> assertThat(user.getLoginInfo().getProviderId()).isEqualTo(user.getLoginInfo().getProviderId()),
            () -> assertThat(user.getLoginInfo().getRefreshToken()).isEqualTo(user.getLoginInfo().getRefreshToken()),
            () -> assertThat(user.getNickname()).isEqualTo(user.getNickname()),
            () -> assertThat(user.getImageUrl()).isEqualTo(user.getImageUrl()),
            () -> assertThat(user.getLocation().getCity()).isEqualTo(user.getLocation().getCity()),
            () -> assertThat(user.getLocation().getDistrict()).isEqualTo(user.getLocation().getDistrict()),
            () -> assertThat(user.getLocation().getUniversity()).isEqualTo(user.getLocation().getUniversity())
        );
    }

    @Test
    void 리프레쉬_토큰으로_유저를_조회한다() {
        User actual = userRepository.findByLoginInfoRefreshToken(user.getLoginInfo().getRefreshToken()).get();

        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.getId()).isNotNull(),
            () -> assertThat(actual.getLoginInfo().getProvider()).isEqualTo(user.getLoginInfo().getProvider()),
            () -> assertThat(actual.getLoginInfo().getProviderId()).isEqualTo(user.getLoginInfo().getProviderId()),
            () -> assertThat(actual.getLoginInfo().getRefreshToken()).isEqualTo(user.getLoginInfo().getRefreshToken()),
            () -> assertThat(actual.getNickname()).isEqualTo(user.getNickname()),
            () -> assertThat(actual.getImageUrl()).isEqualTo(user.getImageUrl()),
            () -> assertThat(actual.getLocation().getCity()).isEqualTo(user.getLocation().getCity()),
            () -> assertThat(actual.getLocation().getDistrict()).isEqualTo(user.getLocation().getDistrict()),
            () -> assertThat(actual.getLocation().getUniversity()).isEqualTo(user.getLocation().getUniversity())
        );
    }

    @Test
    void 공급자ID_공급자이름으로_유저를_조회한다() {
        User actual = userRepository
            .findByLoginInfoProviderIdAndLoginInfoProvider(
                user.getLoginInfo().getProviderId(), user.getLoginInfo().getProvider()
            ).get();
        
        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.getId()).isNotNull(),
            () -> assertThat(actual.getLoginInfo().getProvider()).isEqualTo(user.getLoginInfo().getProvider()),
            () -> assertThat(actual.getLoginInfo().getProviderId()).isEqualTo(user.getLoginInfo().getProviderId()),
            () -> assertThat(actual.getLoginInfo().getRefreshToken()).isEqualTo(user.getLoginInfo().getRefreshToken()),
            () -> assertThat(actual.getNickname()).isEqualTo(user.getNickname()),
            () -> assertThat(actual.getImageUrl()).isEqualTo(user.getImageUrl()),
            () -> assertThat(actual.getLocation().getCity()).isEqualTo(user.getLocation().getCity()),
            () -> assertThat(actual.getLocation().getDistrict()).isEqualTo(user.getLocation().getDistrict()),
            () -> assertThat(actual.getLocation().getUniversity()).isEqualTo(user.getLocation().getUniversity())
        );
    }

    @Test
    void 해당_닉네임을_가진_유저가_있는지_확인한다() {
        boolean actual = userRepository.existsByNickname(user.getNickname());
        assertThat(actual).isTrue();
    }
}