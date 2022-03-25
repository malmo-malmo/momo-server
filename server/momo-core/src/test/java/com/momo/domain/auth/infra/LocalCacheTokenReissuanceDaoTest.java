package com.momo.domain.auth.infra;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.domain.auth.domain.AccessTokenReissuance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Local Cache 엑세스 토큰 재발급 dao 테스트")
public class LocalCacheTokenReissuanceDaoTest {

    private LocalCacheTokenReissuanceDao tokenReissuanceDao;

    @BeforeEach
    void setUp() {
        tokenReissuanceDao = new LocalCacheTokenReissuanceDao();
    }

    @Test
    void 리프레시_토큰과_유저_정보를_맵에_저장한_후_조회한다() {
        tokenReissuanceDao.insert("refresh_token", 1L, "device_code");

        AccessTokenReissuance actual = tokenReissuanceDao.findByRefreshToken("refresh_token").get();
        Assertions.assertAll(
            () -> assertThat(actual.getUserId()).isEqualTo(1L),
            () -> assertThat(actual.getDeviceCode()).isEqualTo("device_code")
        );
    }

    @Test
    void 이미_맵에_존재하는_리프레시_토큰과_유저_정보를_저장하면_덮어씌운다() {
        tokenReissuanceDao.insert("refresh_token", 1L, "device_code1");
        tokenReissuanceDao.insert("refresh_token", 2L, "device_code2");

        AccessTokenReissuance actual = tokenReissuanceDao.findByRefreshToken("refresh_token").get();
        Assertions.assertAll(
            () -> assertThat(actual.getUserId()).isEqualTo(2L),
            () -> assertThat(actual.getDeviceCode()).isEqualTo("device_code2")
        );
    }
}
