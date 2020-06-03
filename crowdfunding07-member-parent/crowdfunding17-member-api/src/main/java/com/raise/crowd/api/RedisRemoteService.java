package com.raise.crowd.api;

import com.raise.crowd.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.TimeUnit;

/**
 * @author zmj
 * @date 2020/6/3 18:16
 * @Description
 */
@FeignClient("raise-crowd-redis")
public interface RedisRemoteService {

    /**
     * 保存redisValue
     * @param key
     * @param value
     * @return ResultEntity<String>
     */
    @RequestMapping("set/redis/key/value/remote")
    ResultEntity<String> setRedisKeyValueRemote(
            @RequestParam("key") String key,
            @RequestParam("value") String value);

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
            @RequestParam("timeunit") TimeUnit timeUnit);

    /**
     * 通过key获取redis的value
     * @param key
     * @return ResultEntity<String>
     */
    @RequestMapping("get/redis/string/value/by/key")
    ResultEntity<String> getRedisStringValueByKey(
            @RequestParam("key") String key);

    /**
     * 通过key删除redis
     * @param key
     * @return
     */
    @RequestMapping("remove/redis/key/remote")
    ResultEntity<String> removeRedisKeyRemote(
            @RequestParam("key") String key);
}
