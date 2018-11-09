package com.mingming.block.trade.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String parseTimeStampToStr(long timeStamp, String format) {
        Date d = new Date(timeStamp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(d);
    }
}
