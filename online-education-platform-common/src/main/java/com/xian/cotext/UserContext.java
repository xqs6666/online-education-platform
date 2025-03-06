package com.xian.cotext;

import java.util.Map;

public class UserContext {
    // 线程安全的ThreadLocal变量 阿萨·
    private static final ThreadLocal<Map<String, Object>> userContext = new ThreadLocal<>();

    // 设置用户信息 到 Thread
    public static void setUser(Map<String, Object> user) {
        userContext.set(user);
    }

    // 获取用户信息
    public static Map<String, Object> getUser() {
        return userContext.get();
    }

    // 清除用户信息
    public static void clear() {
        userContext.remove();
    }
}

