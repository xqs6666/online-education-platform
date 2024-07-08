package com.xian.scheduler;

import com.xian.model.Users;
import com.xian.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserRegisterConsumer {

    private static final String REGISTER_QUEUE = "registerQueue";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private EmailService emailService;

    @Scheduled(fixedDelay = 1000)  // 每 1 秒执行一次
    public void consumeRegisterMessages() {
        log.info("开始处理用户注册消息 每 1 秒执行一次");
        try {
            // 从队列中获取用户信息，并发送邮件
            Users user = (Users) redisTemplate.opsForList().rightPop(REGISTER_QUEUE);
            if (user != null) {
                // 发送欢迎邮件
                String subject = "欢迎注册我们的系统";
                String message = String.format("Hello %s,\n\nWelcome to our online-education-platform!", user.getUsername());
                emailService.sendSimpleMail(user.getEmail(), subject, message);
            }
        } catch (Exception e) {
            // 捕获并记录异常
            log.error("处理消息时出错", e);
        }
    }
}
