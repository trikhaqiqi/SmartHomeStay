package com.codingtest.smarthome.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    public static ZonedDateTime now_with_zone(){
        LocalDateTime ldt = LocalDateTime.now();

        return ldt.atZone(ZoneId.of("Asia/Jakarta"));
    }

    public static ZonedDateTime convert_date_with_start_day(String date){
        String start_date_string = date + " 00:00:00";

        LocalDateTime start_ldt = LocalDateTime.parse(start_date_string, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return start_ldt.atZone(ZoneId.of("Asia/Jakarta"));
    }

    public static ZonedDateTime convert_date_with_end_day(String date){
        String end_date_string = date + " 23:59:59";

        LocalDateTime end_ldt = LocalDateTime.parse(end_date_string, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return end_ldt.atZone(ZoneId.of("Asia/Jakarta"));
    }

    public static ZonedDateTime now_with_hour_starts_day(Integer add){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, add);

        String year = "" + cal.get(Calendar.YEAR);
        String month = "" + (cal.get(Calendar.MONTH) + 1);
        String date = "" + cal.get(Calendar.DATE);

        if((cal.get(Calendar.MONTH) + 1) < 10){
            month = "0" + (cal.get(Calendar.MONTH) + 1);
        }

        if(cal.get(Calendar.DATE) < 10){
            date = "0" + cal.get(Calendar.DATE);
        }

        String date_string = year + "-" + month + "-" + date;
        String start_date_string = date_string + " 00:00:00";

        LocalDateTime start_ldt = LocalDateTime.parse(start_date_string, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return start_ldt.atZone(ZoneId.of("Asia/Jakarta"));
    }

    public static ZonedDateTime now_with_hour_ends_day(Integer add){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, add);

        String year = "" + cal.get(Calendar.YEAR);
        String month = "" + (cal.get(Calendar.MONTH) + 1);
        String date = "" + cal.get(Calendar.DATE);

        if((cal.get(Calendar.MONTH) + 1) < 10){
            month = "0" + (cal.get(Calendar.MONTH) + 1);
        }

        if(cal.get(Calendar.DATE) < 10){
            date = "0" + cal.get(Calendar.DATE);
        }

        String date_string = year + "-" + month + "-" + date;
        String end_date_string = date_string + " 23:59:59";

        LocalDateTime end_ldt = LocalDateTime.parse(end_date_string, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return end_ldt.atZone(ZoneId.of("Asia/Jakarta"));
    }

    public static Date nowDate(){
        Date date = new Date();
        return date;
    }

    public static Integer getLastDayOfTheMonth(String date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateConverterUtil.toDateFormat(date));
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static Integer getTotalDifferenceDay(Date date_1, Date date_2){
        long diffInMillies = Math.abs(date_2.getTime() - date_1.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return Math.toIntExact(diff);
    }

}
