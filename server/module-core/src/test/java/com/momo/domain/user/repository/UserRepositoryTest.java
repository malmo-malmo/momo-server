package com.momo.domain.user.repository;

import com.momo.common.RepositoryTest;
import com.momo.domain.district.entity.City;
import com.momo.domain.user.entity.SocialProvider;
import com.momo.domain.user.entity.User;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("참가자 레포지토리 테스트")
public class UserRepositoryTest extends RepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void before() {
        userRepository.save(
            User.builder()
                .provider(SocialProvider.KAKAO)
                .providerId("test")
                .refreshToken("refresh Token")
                .nickname("testMan")
                .imageUrl("http://~~")
                .city(City.SEOUL)
                .district("마포구")
                .university("한국대")
                .build()
        );
    }

    @Test
    public void 유저를_저장한다() {
        User user = userRepository.findAll().get(0);
        Assertions.assertAll(
            () -> assertThat(user.getId()).isNotNull(),
            () -> assertThat(user.getProvider()).isEqualTo(SocialProvider.KAKAO),
            () -> assertThat(user.getProviderId()).isEqualTo("test"),
            () -> assertThat(user.getRefreshToken()).isEqualTo("refresh Token"),
            () -> assertThat(user.getNickname()).isEqualTo("testMan"),
            () -> assertThat(user.getImageUrl()).isEqualTo("http://~~"),
            () -> assertThat(user.getCity()).isEqualTo(City.SEOUL),
            () -> assertThat(user.getDistrict()).isEqualTo("마포구"),
            () -> assertThat(user.getUniversity()).isEqualTo("한국대")
        );
    }

    @Test
    public void 리프레쉬_토큰으로_유저를_조회한다() {
        User user = getUser(userRepository.findByRefreshToken("refresh Token"));

        Assertions.assertAll(
            () -> assertThat(user).isNotNull(),
            () -> assertThat(user.getId()).isNotNull(),
            () -> assertThat(user.getProvider()).isEqualTo(SocialProvider.KAKAO),
            () -> assertThat(user.getProviderId()).isEqualTo("test"),
            () -> assertThat(user.getRefreshToken()).isEqualTo("refresh Token"),
            () -> assertThat(user.getNickname()).isEqualTo("testMan"),
            () -> assertThat(user.getImageUrl()).isEqualTo("http://~~"),
            () -> assertThat(user.getCity()).isEqualTo(City.SEOUL),
            () -> assertThat(user.getDistrict()).isEqualTo("마포구"),
            () -> assertThat(user.getUniversity()).isEqualTo("한국대")
        );
    }

    @Test
    public void 공급자ID_공급자이름으로_유저를_조회한다() {
        String providerId = "test";
        SocialProvider provider = SocialProvider.KAKAO;

        User user = getUser(userRepository.findByProviderIdAndProvider(providerId, provider));
        Assertions.assertAll(
            () -> assertThat(user).isNotNull(),
            () -> assertThat(user.getId()).isNotNull(),
            () -> assertThat(user.getProvider()).isEqualTo(SocialProvider.KAKAO),
            () -> assertThat(user.getProviderId()).isEqualTo("test"),
            () -> assertThat(user.getRefreshToken()).isEqualTo("refresh Token"),
            () -> assertThat(user.getNickname()).isEqualTo("testMan"),
            () -> assertThat(user.getImageUrl()).isEqualTo("http://~~"),
            () -> assertThat(user.getCity()).isEqualTo(City.SEOUL),
            () -> assertThat(user.getDistrict()).isEqualTo("마포구"),
            () -> assertThat(user.getUniversity()).isEqualTo("한국대")
        );
    }

    @Test
    public void 해당_닉네임을_가진_유저가_있는지_확인한다() {
        boolean isExistsNickname = userRepository.existsByNickname("testMan");
        assertThat(isExistsNickname).isTrue();
    }

    private User getUser(Optional<User> optionalUser) {
        assertThat(optionalUser.isPresent()).isTrue();
        return optionalUser.get();
    }
}
