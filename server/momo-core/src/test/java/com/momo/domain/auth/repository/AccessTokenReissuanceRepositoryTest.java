package com.momo.domain.auth.repository;

import static com.momo.AccessTokenReissuanceFixture.getAccessTokenReissuance;
import static com.momo.Profile.TEST;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.config.EmbeddedRedisConfig;
import com.momo.domain.auth.entity.AccessTokenReissuance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@DataRedisTest
@ActiveProfiles(TEST)
@Import(EmbeddedRedisConfig.class)
@DisplayName("엑세스 토큰 재발급 레포지토리 테스트")
public class AccessTokenReissuanceRepositoryTest {

    @Autowired
    private AccessTokenReissuanceRepository accessTokenReissuanceRepository;

    private AccessTokenReissuance reissuance;

    @BeforeEach
    void setUp() {
        reissuance = accessTokenReissuanceRepository.save(getAccessTokenReissuance());
    }

    @Test
    void 엑세스_토큰_재발급_정보를_조회한다() {
        AccessTokenReissuance expected = accessTokenReissuanceRepository.findById(reissuance.getRefreshToken()).get();

        Assertions.assertAll(
            () -> assertThat(expected.getRefreshToken()).isEqualTo(reissuance.getRefreshToken()),
            () -> assertThat(expected.getUserId()).isEqualTo(reissuance.getUserId()),
            () -> assertThat(expected.getDeviceCode()).isEqualTo(reissuance.getDeviceCode())
        );
    }

    @Test
    void 엑세스_토큰_재발급_정보를_삭제한다() {
        accessTokenReissuanceRepository.delete(reissuance);
        boolean expected = accessTokenReissuanceRepository.existsById(reissuance.getRefreshToken());

        assertThat(expected).isFalse();
    }
}
