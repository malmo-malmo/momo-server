package com.momo.domain.auth.domain;

import static com.momo.AccessTokenReissuanceFixture.getAccessTokenReissuance;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("AccessTokenReissuance POJO 테스트")
public class AccessTokenReissuanceTest {

    private AccessTokenReissuance reissuance;

    @BeforeEach
    void setUp() {
        reissuance = getAccessTokenReissuance();
    }

    @Test
    void 기기_고유번호가_동일한지_확인한다() {
        boolean expected = reissuance.isSameDeviceCode(reissuance.getDeviceCode());
        assertThat(expected).isTrue();
    }

    @Test
    void 기기_고유번호가_널인_경우_동일한지_확인한다() {
        boolean expected = reissuance.isSameDeviceCode(null);
        assertThat(expected).isFalse();
    }
}
