package com.dateutils;

import java.time.LocalDateTime;

/**
 * Project: Utils
 * Create By: Chen.F.X
 * DateTime: 2024-09-22 11:02
 * <p>
 * Desc:
 */
public class DateUtils {
    /**
     * 返回年月日信息
     * @return
     */
    public static String getYearMonthDay() {
        LocalDateTime localDateTime = LocalDateTime.now();
        int year = localDateTime.getYear();
        int month = localDateTime.getMonthValue();
        int day = localDateTime.getDayOfMonth();
        String yearMonthDay = year + "-" + month + "-" + day;
        return yearMonthDay;
    }
}
