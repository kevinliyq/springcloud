package com.alang.study.springcloud.serviceribbon.controller;

import com.alang.study.springcloud.serviceribbon.service.HelloFeignService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController {

	@Autowired
	private HelloFeignService helloService;

	@GetMapping("/hello/{name}")
	public String sayHelloToService(@PathVariable("name") String name){
		return helloService.hello(name);
	}

	@SentinelResource(value = "sentinel-demo", blockHandler = "handleException")
	@GetMapping("/sentinel-demo")
	public String sentinelExample(@RequestParam("name") String name)
	{
		if ("kill".equals(name))
		{
			throw new RuntimeException("RuntimeException, check fallback");
		}

		return new Date().toString();
	}

	public String handleException(String name, BlockException exception)
	{
		return exception.getRule() + " " + exception.getRuleLimitApp() + " " + exception.getMessage();
	}

	public String fallback(String name)
	{
		return "fallback " + name + " is called";
	}

}
