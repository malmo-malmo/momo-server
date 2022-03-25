package com.momo.domain.auth.infra;

import com.momo.domain.auth.domain.AccessTokenReissuance;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;

public class RedisTokenReissuanceDao implements TokenReissuanceDao {

    private final RedisTemplate redisTemplate;
    private final HashMapper<Object, byte[], byte[]> mapper;
    private final long accessTokenExpirationMsec;

    public RedisTokenReissuanceDao(
        RedisTemplate redisTemplate,
        @Value("${app.auth.access-token-expiration-msec}") long accessTokenExpirationMsec
    ) {
        this.redisTemplate = redisTemplate;
        this.mapper = new ObjectHashMapper();
        this.accessTokenExpirationMsec = accessTokenExpirationMsec;
    }

    @Override
    public void insert(String refreshToken, Long userId, String deviceCode) {
        HashOperations<String, byte[], byte[]> hashOperations = redisTemplate.opsForHash();

        Map<byte[], byte[]> mappedHash = mapper.toHash(AccessTokenReissuance.create(userId, deviceCode));
        hashOperations.putAll(refreshToken, mappedHash);
        redisTemplate.expire(refreshToken, accessTokenExpirationMsec, TimeUnit.MILLISECONDS);
    }

    @Override
    public Optional<AccessTokenReissuance> findByRefreshToken(String refreshToken) {
        HashOperations<String, byte[], byte[]> hashOperations = redisTemplate.opsForHash();

        Map<byte[], byte[]> loadedHash = hashOperations.entries(refreshToken);
        if (loadedHash.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of((AccessTokenReissuance) mapper.fromHash(loadedHash));
    }
}
