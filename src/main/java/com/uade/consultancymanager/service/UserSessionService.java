package com.uade.consultancymanager.service;

/**
import com.uade.consultancymanager.model.UserSession;
import com.uade.consultancymanager.repository.SesionUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserSessionService {

    @Autowired
    private SesionUsuarioRepository userRepository;

    @Autowired
    private RedisService redisService;

    public UserSession getUserById(Long userId) {
        String redisKey = "user:" + userId;
        UserSession user = (UserSession) redisService.get(redisKey);

        if (user == null) {
            user = userRepository.findById(userId).orElse(null);
            if (user != null) {
                redisService.save(redisKey, user, 10, TimeUnit.MINUTES);
            }
        }

        return user;
    }
}
**/
