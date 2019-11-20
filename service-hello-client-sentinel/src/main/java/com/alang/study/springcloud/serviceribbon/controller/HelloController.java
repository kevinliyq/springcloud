package com.alang.study.springcloud.serviceribbon.controller;

import com.alang.study.springcloud.serviceribbon.service.HelloFeignService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Autowired
	private HelloFeignService helloService;

	//@SentinelResource("hello")
	@GetMapping("/hello/{name}")
	public String sayHelloToService(@PathVariable("name") String name){
		
		return helloService.hello(name);
	}
}
