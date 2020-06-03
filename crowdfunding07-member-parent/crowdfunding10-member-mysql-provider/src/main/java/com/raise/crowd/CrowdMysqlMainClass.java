package com.raise.crowd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zmj
 * @date 2020/6/3 14:52
 * @Description
 */
@MapperScan("com.raise.crowd.mapper")
@SpringBootApplication
public class CrowdMysqlMainClass {
    public static void main(String[] args) {
        SpringApplication.run(CrowdMysqlMainClass.class, args);
    }
}
