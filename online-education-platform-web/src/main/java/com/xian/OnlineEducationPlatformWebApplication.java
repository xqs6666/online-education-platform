package com.xian;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@MapperScan("com.xian.mapper")
@EnableScheduling  // 启用定时任务

public class OnlineEducationPlatformWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineEducationPlatformWebApplication.class, args);
    }
}
