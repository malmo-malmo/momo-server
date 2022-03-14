package com.momo.config;

import static com.momo.Profile.LOCAL;
import static com.momo.Profile.TEST;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

@Configuration
@Profile(value = {TEST, LOCAL})
public class EmbeddedRedisConfig {

    private RedisServer redisServer;

    @PostConstruct
    public void redisServer() {
        redisServer = new RedisServer(6379);
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }
}
