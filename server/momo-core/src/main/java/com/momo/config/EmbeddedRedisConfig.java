package com.momo.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import redis.embedded.RedisServer;

/*
@Configuration
@Profile(value = {TEST, LOCAL})
*/
public class EmbeddedRedisConfig {

    private RedisServer redisServer;

    @PostConstruct
    public void redisServer() {
        redisServer = new RedisServer(6380);
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }
}
