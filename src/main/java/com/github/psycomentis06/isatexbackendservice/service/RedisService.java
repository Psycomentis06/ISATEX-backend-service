package com.github.psycomentis06.isatexbackendservice.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public static String getRestPasswordTokenKey(String userId) {
        return "user:" + userId + ":password:token";
    }

    public void setUserResetPasswordToken(String userId, String token) {
        redisTemplate.opsForValue().set(getRestPasswordTokenKey(userId), token, 15, TimeUnit.MINUTES);
    }

    public String getResetPasswordToken(String userId) {
        return (String) redisTemplate.opsForValue().get(getRestPasswordTokenKey(userId));
    }

    public void removeResetPasswordToken(String userId) {
        redisTemplate.opsForValue().getAndDelete(getResetPasswordToken(userId));
    }
}
