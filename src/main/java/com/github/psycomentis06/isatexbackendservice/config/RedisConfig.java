package com.github.psycomentis06.isatexbackendservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.hostname}")
    private String redisLocalhost;

    @Value("${spring.data.redis.port}")
    private String redisPort;

    @Bean
    RedisConnectionFactory redisConnectionFactoryBean() {
       return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisLocalhost, Integer.parseInt(redisPort)));
    }

    @Bean
    RedisTemplate<String, Object> redisTemplateBean() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactoryBean());
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return redisTemplate;
    }
}
