package com.momo.domain.auth.infra;

import com.momo.domain.auth.domain.AccessTokenReissuance;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class LocalCacheTokenReissuanceDao implements TokenReissuanceDao {

    private final ConcurrentHashMap<String, AccessTokenReissuance> storage;

    public LocalCacheTokenReissuanceDao() {
        this.storage = new ConcurrentHashMap<>();
    }

    @Override
    public void insert(String refreshToken, Long userId, String deviceCode) {
        storage.put(refreshToken, AccessTokenReissuance.create(userId, deviceCode));
    }

    @Override
    public Optional<AccessTokenReissuance> findByRefreshToken(String refreshToken) {
        return Optional.ofNullable(storage.get(refreshToken));
    }
}
