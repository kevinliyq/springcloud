package com.alang.study.springcloud.serviceribbon.service;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceFallbackFactory implements FallbackFactory<HelloServiceFallback> {
    @Override
    public HelloServiceFallback create(Throwable throwable) {
        return new HelloServiceFallback(throwable);
    }
}
