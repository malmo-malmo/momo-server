package com.momo.domain.auth.infra;

import com.momo.domain.auth.domain.AccessTokenReissuance;
import java.util.Optional;

public interface TokenReissuanceDao {

    void insert(String refreshToken, Long userId, String deviceCode);

    Optional<AccessTokenReissuance> findByRefreshToken(String refreshToken);
}
