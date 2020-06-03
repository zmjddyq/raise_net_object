package com.raise.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zmj
 * @date 2020/6/3 12:18
 * @Description
 */

@EnableEurekaServer
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class CrowdEurekaMainClass {
    public static void main(String[] args) {
        SpringApplication.run(CrowdEurekaMainClass.class, args);
    }
}