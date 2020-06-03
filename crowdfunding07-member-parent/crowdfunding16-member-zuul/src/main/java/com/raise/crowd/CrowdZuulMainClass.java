package com.raise.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author zmj
 * @date 2020/6/3 19:58
 * @Description
 */
@EnableZuulProxy
@SpringBootApplication
public class CrowdZuulMainClass {
    public static void main(String[] args) {
        SpringApplication.run(CrowdZuulMainClass.class, args);
    }
}
