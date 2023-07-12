package com.codingtest.smarthome.controllers;

import com.codingtest.smarthome.utils.SecurityUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexApi {

    @GetMapping
    public String index(){
        return "Smart home Service V1";
    }

    @GetMapping("generate_salt_and_pass/{password}")
    public String generateSaltAndPass(@PathVariable String password){
        String salt = SecurityUtil.generateSalt();
        String pwd = SecurityUtil.encrypt(password, salt);
        return salt + " " + pwd;
    }
}
