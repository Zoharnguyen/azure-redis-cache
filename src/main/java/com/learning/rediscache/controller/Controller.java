package com.learning.rediscache.controller;

import com.learning.rediscache.model.Entity;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
public class Controller {

    StringRedisTemplate stringRedisTemplate;
    public ValueOperations<String, String> redisExecute;

    public Controller(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
        redisExecute = this.stringRedisTemplate.opsForValue();
    }

    @PostMapping("/post")
    public String postValue(@RequestBody Entity entity) {

        if(!stringRedisTemplate.hasKey(entity.getKey())) {
            redisExecute.set(entity.getKey(), entity.getValue());
            return "Sucess";
        } else return "fail";

    }

    @GetMapping("/get")
    public String getValue(@RequestParam("key") String key) {
        return redisExecute.get(key);
    }

}
