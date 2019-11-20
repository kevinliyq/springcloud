package com.alang.study.springcloud.serviceribbon.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloServiceFallback implements HelloFeignService {
    private Logger logger = LoggerFactory.getLogger(HelloServiceFallback.class);

    private Throwable ex;

    public HelloServiceFallback(Throwable ex) {
        this.ex = ex;
    }

    @Override
    public String hello(String name) {
        String fallbackMsg = "Fallback " + name + " with message " + ex.getMessage();
        logger.info(fallbackMsg);

        return fallbackMsg;
    }
}
