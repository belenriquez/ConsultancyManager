package com.uade.consultancymanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // Método para guardar datos en Redis
    public void save(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    // Método para obtener datos de Redis
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // Método para eliminar datos de Redis
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
