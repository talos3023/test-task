package com.flatrock.task.orderservice.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RedisConfiguration {
    private final RedisClient redisClient;

    public RedisConfiguration(Environment environment) {
        String host = environment.getProperty("redis.host");
        this.redisClient = RedisClient
                .create(RedisURI.Builder
                        .redis(host, 6379)
                        .build());
    }

    @Bean
    public RedisPubSubCommands<String, String> redisSynchronousPublisher() {
        return redisClient.connectPubSub().sync();
    }
}
