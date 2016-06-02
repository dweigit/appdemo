package com.xsoft.appdemo.redis;

import com.xsoft.appdemo.model.UserInfo;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by dengwei06015 on 2016/6/1.
 */
public class UserStorage {
    private RedisTemplate<String, UserInfo> redisTemplate;

    public void addOrUpdate(UserInfo user) {
        redisTemplate.opsForValue().set("user.userid." + user.getUserId(), user);
    }

    public UserInfo load(int userId) {
        return redisTemplate.opsForValue().get("user.userid." + userId);
    }

    public void delete(int userId) {
        redisTemplate.delete("user.userid." + userId);
    }

    public RedisTemplate<String, UserInfo> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, UserInfo> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
