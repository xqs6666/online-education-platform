package com.xian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xian.mapper")
public class OnlineEducationPlatformWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineEducationPlatformWebApplication.class, args);
    }
}
