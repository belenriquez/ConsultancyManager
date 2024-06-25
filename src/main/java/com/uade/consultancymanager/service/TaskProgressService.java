package com.uade.consultancymanager.service;
/**
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TaskProgressService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void saveTaskProgress(String taskId, TaskProgress taskProgress, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set("task_progress:" + taskId, taskProgress, timeout, unit);
    }

    public TaskProgress getTaskProgress(String taskId) {
        return (TaskProgress) redisTemplate.opsForValue().get("task_progress:" + taskId);
    }

    public void deleteTaskProgress(String taskId) {
        redisTemplate.delete("task_progress:" + taskId);
    }
}
**/