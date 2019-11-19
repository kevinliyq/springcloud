package com.alang.study.springcloud.serviceribbon.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="service-hello-nacos", fallbackFactory = HelloServiceFallbackFactory.class)
public interface HelloFeignService {

    @GetMapping(value = "/hello")
    String hello(@RequestParam("name") String name);
}
