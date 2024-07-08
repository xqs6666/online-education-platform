package com.xian.cotext;

import java.util.Map;

public class UserContext {
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

