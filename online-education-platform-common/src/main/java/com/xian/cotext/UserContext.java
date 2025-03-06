package com.xian.cotext;

import java.util.Map;

public class UserContext {
    // 线程安全的ThreadLocal变量
    private static final ThreadLocal<Map<String, Object>> userContext = new ThreadLocal<>();

    public static void setUser(Map<String, Object> user) {
        userContext.set(user);
    }

    public static Map<String, Object> getUser() {
        return userContext.get();
    }

    public static void clear() {
        userContext.remove();
    }
}

