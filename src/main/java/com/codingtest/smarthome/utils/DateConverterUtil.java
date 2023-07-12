package com.codingtest.smarthome.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateConverterUtil {

    public static String currentTimeMilis() {
        return "" + System.currentTimeMillis();
    }

    public static String getTodayDate() {
        Calendar cal = Calendar.getInstance();
        int year  = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date  = cal.get(Calendar.DATE);
        cal.clear();
        cal.set(year, month, date);
        return "" + cal.getTimeInMillis();
    }

    public static String toStringFormat(Date date){
        if (date == null){
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static String getTimeStringFormat(Date date){
        if (date == null){
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(date);
    }

    public static String toStringTimeFormat(ZonedDateTime date){
        if (date == null){
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date.toInstant());
    }

    public static String toStringTimeFormat(Date date){
        if (date == null){
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public static Date toDateTimeFormat(String dateString){
        String[] dateStringSplit = dateString.split(" ");
        if (dateStringSplit.length > 1){
            dateString = dateStringSplit[0] + " 07:00:00";
        }
        else{
            dateString = dateString + " 07:00:00";
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Date toDateTimeFormat2(String dateTimeString){
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Date getSilverDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        return calendar.getTime();
    }

    public static Date getGoldDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 60);
        return calendar.getTime();
    }

    public static Date getPlatinumDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 90);
        return calendar.getTime();
    }

    public static Date toDateFormat(String dateString){
        dateString = dateString + " 07:00:00";
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Date toStartDayTime(String dateString){
        dateString = dateString + " 00:00:00";
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Date toEndDayTime(String dateString){
        dateString = dateString + " 23:59:59";
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ZonedDateTime toZonedDateTime(Date date){
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static ZonedDateTime generateExpiredTwoFactorDate(){
        Date date = new Date();
        Calendar calendar = calendarWithJakartaZone();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, 5);

        return toZonedDateTime(calendar.getTime());
    }

    public static Date nowWithJakartaZone(){
        Calendar calendar = calendarWithJakartaZone();
        return calendar.getTime();
    }

    public static Date nowWithMakassarZone(){
        Calendar calendar = calendarWithMakassarZone();
        return calendar.getTime();
    }

    public static Date nowWithJayapuraZone(){
        Calendar calendar = calendarWithJayapuraZone();
        return calendar.getTime();
    }

    public static Calendar calendarWithJakartaZone(){
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(Constant.DEFAULT_TIMEZONE);
        return calendar;
    }

    public static Calendar calendarWithMakassarZone(){
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(Constant.WIT_TIMEZONE);
        return calendar;
    }

    public static Calendar calendarWithJayapuraZone(){
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(Constant.WITA_TIMEZONE);
        return calendar;
    }

    public static String substractTime(String date, String time, Integer minute) throws ParseException {
        String dateTime = date + " " + time;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(Constant.DEFAULT_TIMEZONE);
        calendar.setTime(formatter.parse(dateTime));
        calendar.set(Calendar.MINUTE, -minute);

        String resultDate = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE);
        String timeDate = calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE);

        return resultDate + " " + timeDate;
    }

    public static String getDay(Date date){
        if (date != null){
            String[] days = {"Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jum'at", "Sabtu"};

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            return days[calendar.get(Calendar.DAY_OF_WEEK) - 1];
        }

        return null;
    }

    public static String getDayEng(Date date){
        if (date != null){
            String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            return days[calendar.get(Calendar.DAY_OF_WEEK) - 1];
        }

        return null;
    }
}
