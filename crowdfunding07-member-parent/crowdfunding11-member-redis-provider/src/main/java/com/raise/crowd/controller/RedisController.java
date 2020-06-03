package com.raise.crowd.controller;

import com.raise.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author zmj
 * @date 2020/6/3 18:35
 * @Description
 */
@RestController
public class RedisController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 保存redisValue
     * @param key
     * @param value
     * @return ResultEntity<String>
     */
    @RequestMapping("set/redis/key/value/remote")
    ResultEntity<String> setRedisKeyValueRemote(
            @RequestParam("key") String key,
            @RequestParam("value") String value) {
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            return ResultEntity.failed(e.getMessage());
        }
    }

    /**
     * 保存redisValue(可以设置超时时间)
     * @param key
     * @param value
     * @param time
     * @param timeUnit
     * @return ResultEntity<String>
     */
    @RequestMapping("set/redis/key/value/remote/with/timeout")
    ResultEntity<String> setRedisKeyValueRemoteWithTimeout(
            @RequestParam("key") String key,
            @RequestParam("value") String value,
            @RequestParam("time") Long time,
            @RequestParam("timeunit") TimeUnit timeUnit) {
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key, value, time, timeUnit);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            return ResultEntity.failed(e.getMessage());
        }
    }

    /**
     * 通过key获取redis的value
     * @param key
     * @return ResultEntity<String>
     */
    @RequestMapping("get/redis/string/value/by/key")
    ResultEntity<String> getRedisStringValueByKey(
            @RequestParam("key") String key) {
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            String s = operations.get(key);
            return ResultEntity.successWithData(s);
        } catch (Exception e) {
            return ResultEntity.failed(e.getMessage());
        }
    }

    /**
     * 通过key删除redis
     * @param key
     * @return
     */
    @RequestMapping("remove/redis/key/remote")
    ResultEntity<String> removeRedisKeyRemote(
            @RequestParam("key") String key) {
        try {
            redisTemplate.delete(key);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            return ResultEntity.failed(e.getMessage());
        }
    }
}
