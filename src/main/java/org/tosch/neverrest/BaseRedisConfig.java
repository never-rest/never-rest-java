package org.tosch.neverrest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

public class BaseRedisConfig {

    @Value("${redis.hostName}")
    private String redisHostName;

    @Value("${redis.port}")
    private int redisPort;

    @Value("${redis.password:}")
    private String redisPassword;

    @Value("${redis.useSsl:True}")
    private Boolean redisUseSsl;

    @Value("${redis.usePooling:True}")
    private Boolean redisUsePooling;

    @Value("${redis.useLocking:False}")
    private Boolean redisUseLocking;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisHostName);
        redisStandaloneConfiguration.setPort(redisPort);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(redisPassword));
        redisPassword = null;
        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfigurationBuilder = JedisClientConfiguration.builder();

        if (redisUseSsl) {
            jedisClientConfigurationBuilder = jedisClientConfigurationBuilder.useSsl().and();
        }

        if (redisUsePooling) {
            jedisClientConfigurationBuilder = jedisClientConfigurationBuilder.usePooling().and();
        }

        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfigurationBuilder.build());
    }

    @Bean
    RedisCacheWriter redisCacheWriter() {
        if (redisUseLocking) {
            return RedisCacheWriter.lockingRedisCacheWriter(jedisConnectionFactory());
        } else {
            return RedisCacheWriter.nonLockingRedisCacheWriter(jedisConnectionFactory());
        }
    }

    @Bean
    RedisCacheConfiguration redisCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig();
    }

    @Bean
    RedisCacheManager cacheManager() {
        return new RedisCacheManager(redisCacheWriter(), redisCacheConfiguration());
    }
}
