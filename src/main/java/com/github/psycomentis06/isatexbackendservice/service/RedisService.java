package com.github.psycomentis06.isatexbackendservice.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public static String getPasswordResetToken(String userId) {
        return "user:" + userId + ":password:token";
    }

    public void setUserResetPasswordToken(String userId, String token) {
        redisTemplate.opsForValue().set(getPasswordResetToken(userId), token);
    }
}
