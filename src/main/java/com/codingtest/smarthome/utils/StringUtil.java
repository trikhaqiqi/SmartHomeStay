package com.codingtest.smarthome.utils;

import java.util.Random;

public class StringUtil {

    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static String generateTwoFactorCode(){
        StringBuilder twoFactorCode = new StringBuilder();
        Random rand = new Random();
        for (Integer counter = 0; counter < 6; counter++){
            twoFactorCode.append(rand.nextInt(10));
        }

        return twoFactorCode.toString();
    }
}
