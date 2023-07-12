package com.codingtest.smarthome.configs.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class CheckHeaderServiceInterceptorAppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    CheckHeaderServiceInterceptor checkHeaderServiceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkHeaderServiceInterceptor);
        super.addInterceptors(registry);
    }
}
