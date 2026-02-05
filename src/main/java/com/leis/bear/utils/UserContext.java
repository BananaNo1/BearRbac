package com.leis.bear.utils;

public class UserContext {
    private static final ThreadLocal<Integer> t1 = new ThreadLocal<>();

    public static void setUser(Integer userId) {
        t1.set(userId);
    }

    public static Integer getUser() {
        return t1.get();
    }

    public static void removeUser() {
        t1.remove();
    }
}
