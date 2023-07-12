package com.codingtest.smarthome.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class FileUtil {

    public static String baseUrlService = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

    public static String generatePackageCoverLink(String id){
        return baseUrlService + "/v1/package/get-cover/" + id;
    }

    public static String generateProductFileDownloadLink(String productFileDownloadId) {
        return baseUrlService + "/v1/product/download-file/" + productFileDownloadId;
    }

}
