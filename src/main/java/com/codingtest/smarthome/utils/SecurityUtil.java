package com.codingtest.smarthome.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class SecurityUtil {

    public static String generateSalt() {
        return BCrypt.gensalt(10);
    }

    public static String encrypt(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }

    public static boolean match(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public static String generateVerificationCode() {
        return BCrypt.gensalt(30).replace("/", "");
    }

}
