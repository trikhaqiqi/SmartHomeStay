package com.codingtest.smarthome.utils;

import java.math.BigInteger;
import java.util.TimeZone;

public class Constant {

    public static final String APP_NAME = "smarthome Service Dev";
    public static final String APP_VERSION = "1.0";

    public static final int DISPLAY_LENGTH = 10;
    public static final int SUCCESS_RESPONSE = 200;
    public static final int ERROR_RESPONSE = 500;

    public static final String PEPPER = "smarthome_service";

    public static final int DEFAULT_MIN_PASSWORD_LENGTH = 10;

    public static final BigInteger MAX_CHECKIN_RADIUS = BigInteger.valueOf(3000);

    public static final TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("Asia/Jakarta");

    public static final TimeZone WITA_TIMEZONE = TimeZone.getTimeZone("Asia/Makassar");

    public static final TimeZone WIT_TIMEZONE = TimeZone.getTimeZone("Asia/Jayapura");

    public static final String[] URI_ALL_ROLE = {
            "/",
            "/swagger-ui.html",
            "/v1/authentication/login",
            "/v1/support/test-send-email-new-order",
            "/error"
    };

    public static final Boolean array_in(String[] books, String book){
        for(int i = 0; i < books.length; i++){
            if(books[i].equals(book)){
                return true;
            }
        }

        return false;
    }

    public static final String FLUTTER_NOTIFICATION_CLICK = "FLUTTER_NOTIFICATION_CLICK";
}
