package com.alang.study.springcloud.servicefeign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="service-hello",fallback = SchedualServiceHiHystric.class)
//use @FeginClient to indicate which service will be called.
public interface SchedualServiceHello {

	//@LoadBalanced
	@GetMapping("/hello")
	public String hello(@RequestParam(value="name") String name);
}
