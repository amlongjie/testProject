package com.mingming.block.trade.utils;

import java.time.LocalDateTime;

public class TimeUtils {

    /**
     * 当前时间小于几点
     *
     * @param hour
     * @return
     */
    public static boolean hourLessThan(int hour) {
        LocalDateTime now = LocalDateTime.now();
        return now.getHour() < hour;
    }
}
