package com.logutils;

/**
 * Project: Utils
 * Create By: Chen.F.X
 * DateTime: 2024-09-22 10:52
 * <p>
 * Desc: Log 工具类
 */
public class Logit {
    public static void d(String tag, String msg) {
        System.out.println(tag + " " + msg);
    }

    public static void d(String tag, String msg, Throwable throwable) {
        System.out.println(tag + " " + msg + "\n" + throwable.getMessage());
    }
}
