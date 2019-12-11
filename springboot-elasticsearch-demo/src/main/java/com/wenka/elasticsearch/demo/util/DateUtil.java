package com.wenka.elasticsearch.demo.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created with IDEA
 *
 * @author wenka wkwenka@gmail.com
 * @date 2019/12/10  下午 04:27
 * @description:
 */
public class DateUtil {

    public static Date getDate(int year, int month, int day) {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, year);
        instance.set(Calendar.MONTH, month - 1);
        instance.set(Calendar.DAY_OF_MONTH, day);
        return instance.getTime();
    }

    public static void main(String[] args) {
        System.out.println(getDate(2019, 2, 11));
    }
}
