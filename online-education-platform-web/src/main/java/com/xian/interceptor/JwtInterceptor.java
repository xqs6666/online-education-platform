package com.xian.interceptor;

import com.xian.cotext.UserContext;
import com.xian.properties.JwtProperties;
import com.xian.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private JwtProperties jwtProperties;

    private final static String USER_TOKEN = "user:";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在请求处理之前调用
        log.info("JwtInterceptor拦截器运行");
        String requestURI = request.getRequestURI();
        log.info("拦截URL:[{}]",requestURI);
        final String token;
        // 获取请求头中的token
        token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 验证token
        Claims claims = JwtUtil.validateToken(jwtProperties.getSecret(), token);
        if (claims == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 获取jwt用户信息
        Integer userId = claims.get("userId", Integer.class);

        // 验证redis是否有用户信息
        Map<String, Object> userInfo = (Map<String, Object>) redisTemplate.opsForValue().get(USER_TOKEN+userId);
        log.info("redis中用户信息为："+userInfo);
        if (userInfo == null ) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 设置用户信息到 ThreadLocal
        Map<String, Object> user=new HashMap<>();
        user.put("userId", userId);
        String username = (String) userInfo.get("username");
        String userType = (String) userInfo.get("userType");
        user.put("username", username);
        user.put("userType", userType);
        UserContext.setUser(user);
        return true;
    }


    //这个方法在每个请求完成后都会被调用一次，以确保所有请求都能得到妥善处理。
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 在整个请求处理完成后调用
        // 这里可以编写一些资源清理的逻辑
        // 清除 ThreadLocal 中的用户信息
        UserContext.clear();
    }
}