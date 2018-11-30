package com.trois.project.emo.test.Chatbot.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
    //1. date_for_chat 채팅 프로토콜에 맞춘 포멧 yyyy-MM-dd HH:mm
    public static String date_for_chat() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formatDate = sdfNow.format(date);
        return formatDate;
    }

    //2. date_month_day_time: MMdd+오후/오전+hh:mm
    public static String date_month_day_time() {
        Date date = new Date(System.currentTimeMillis());
        String am_or_pm = new SimpleDateFormat("aa").format(date);
        String hour_and_minute = new SimpleDateFormat("hh:mm").format(date);
        String month_and_day = new SimpleDateFormat("MMdd").format(date);
        String am_or_pm_result = "";

        if (am_or_pm.equals("am") || am_or_pm.equals("AM")) {
            am_or_pm_result = "오전";
        } else if (am_or_pm.equals("pm") || am_or_pm.equals("AM")) {
            am_or_pm_result = "오후";
        } else {
            am_or_pm_result = am_or_pm;
        }

        return new StringBuilder(month_and_day).append(" ").append(am_or_pm_result).append(" ").append(hour_and_minute).toString();
    }

    //3. date_apm:오후/오전+hh:mm
    public static String date_apm() {
        Date date = new Date(System.currentTimeMillis());
        String am_or_pm = new SimpleDateFormat("aa").format(date);
        String am_or_pm_result = " ";
        if (am_or_pm.equals("am") || am_or_pm.equals("AM")) {
            am_or_pm_result = "오전";
        } else if (am_or_pm.equals("pm") || am_or_pm.equals("AM")) {
            am_or_pm_result = "오후";
        } else {
            am_or_pm_result = am_or_pm;
        }

        String hour_and_minute = new SimpleDateFormat("hh:mm").format(date);


        return new StringBuilder(am_or_pm_result).append(" ").append(hour_and_minute).toString();
    }

    //4. MMdd : 0월0일
    public static String date_month_and_day() {
        Date date = new Date(System.currentTimeMillis());
        String month_and_day = new SimpleDateFormat("MMdd").format(date);
        return month_and_day;
    }


    //5. 월
    public static String date_month() {
        Date date = new Date(System.currentTimeMillis());
        String month = new SimpleDateFormat("MM").format(date);
        return month;
    }

    //6. 일
    public static String date_day() {
        Date date = new Date(System.currentTimeMillis());
        String day = new SimpleDateFormat("dd").format(date);
        return day;
    }

    //7. 나열 yyMMddHHmmss --> 이미지 업로드시 고유네임으로 쓴다
    public static String date_full() {
        Date date = new Date(System.currentTimeMillis());
        String date_full = new SimpleDateFormat("yyMMddHHmmss").format(date);
        return date_full;
    }

}

