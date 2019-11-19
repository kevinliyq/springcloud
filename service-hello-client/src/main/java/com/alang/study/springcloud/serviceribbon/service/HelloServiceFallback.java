package com.alang.study.springcloud.serviceribbon.service;

public class HelloServiceFallback implements HelloFeignService {
    private Throwable ex;

    public HelloServiceFallback(Throwable ex) {
        this.ex = ex;
    }

    @Override
    public String hello(String name) {
        return "Fallback " + name + " with message " + ex.getMessage();
    }
}
