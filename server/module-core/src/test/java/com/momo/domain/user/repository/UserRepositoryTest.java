package com.momo.domain.user.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.RepositoryTest;
import com.momo.domain.district.entity.City;
import com.momo.domain.user.entity.SocialProvider;
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
        user = userRepository.save(
            User.builder()
                .provider(SocialProvider.KAKAO)
                .providerId("test")
                .refreshToken("refreshToken")
                .nickname("닉네임")
                .imageUrl("이미지 URL")
                .city(City.SEOUL)
                .district("마포구")
                .university("서울대학교")
                .build()
        );
    }

    @Test
    void 유저를_저장한다() {
        User user = userRepository.findAll().get(0);
        Assertions.assertAll(
            () -> assertThat(user.getId()).isNotNull(),
            () -> assertThat(user.getProvider()).isEqualTo(user.getProvider()),
            () -> assertThat(user.getProviderId()).isEqualTo(user.getProviderId()),
            () -> assertThat(user.getRefreshToken()).isEqualTo(user.getRefreshToken()),
            () -> assertThat(user.getNickname()).isEqualTo(user.getNickname()),
            () -> assertThat(user.getImageUrl()).isEqualTo(user.getImageUrl()),
            () -> assertThat(user.getCity()).isEqualTo(user.getCity()),
            () -> assertThat(user.getDistrict()).isEqualTo(user.getDistrict()),
            () -> assertThat(user.getUniversity()).isEqualTo(user.getUniversity())
        );
    }

    @Test
    void 리프레쉬_토큰으로_유저를_조회한다() {
        User actual = userRepository.findByRefreshToken(user.getRefreshToken()).get();
        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.getId()).isNotNull(),
            () -> assertThat(actual.getProvider()).isEqualTo(user.getProvider()),
            () -> assertThat(actual.getProviderId()).isEqualTo(user.getProviderId()),
            () -> assertThat(actual.getRefreshToken()).isEqualTo(user.getRefreshToken()),
            () -> assertThat(actual.getNickname()).isEqualTo(user.getNickname()),
            () -> assertThat(actual.getImageUrl()).isEqualTo(user.getImageUrl()),
            () -> assertThat(actual.getCity()).isEqualTo(user.getCity()),
            () -> assertThat(actual.getDistrict()).isEqualTo(user.getDistrict()),
            () -> assertThat(actual.getUniversity()).isEqualTo(user.getUniversity())
        );
    }

    @Test
    void 공급자ID_공급자이름으로_유저를_조회한다() {
        User actual = userRepository.findByProviderIdAndProvider(user.getProviderId(), user.getProvider()).get();
        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.getId()).isNotNull(),
            () -> assertThat(actual.getProvider()).isEqualTo(user.getProvider()),
            () -> assertThat(actual.getProviderId()).isEqualTo(user.getProviderId()),
            () -> assertThat(actual.getRefreshToken()).isEqualTo(user.getRefreshToken()),
            () -> assertThat(actual.getNickname()).isEqualTo(user.getNickname()),
            () -> assertThat(actual.getImageUrl()).isEqualTo(user.getImageUrl()),
            () -> assertThat(actual.getCity()).isEqualTo(user.getCity()),
            () -> assertThat(actual.getDistrict()).isEqualTo(user.getDistrict()),
            () -> assertThat(actual.getUniversity()).isEqualTo(user.getUniversity())
        );
    }

    @Test
    void 해당_닉네임을_가진_유저가_있는지_확인한다() {
        boolean actual = userRepository.existsByNickname(user.getNickname());
        assertThat(actual).isTrue();
    }
}
