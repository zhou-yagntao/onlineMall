package com.zyt.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    //日期转换字符串
    public static String dateString(Date date, String patt) {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        String format = sdf.format(date);
        return format;
    }


    //字符串转换日期
    public static Date stringDate(String str, String patt) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date format = sdf.parse(str);
        return format;
    }

}
