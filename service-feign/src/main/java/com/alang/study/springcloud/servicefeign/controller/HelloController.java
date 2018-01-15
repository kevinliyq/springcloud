package com.alang.study.springcloud.servicefeign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alang.study.springcloud.servicefeign.service.SchedualServiceHello;

@RestController
public class HelloController {

	@Autowired
	private SchedualServiceHello helloService;
	
	@GetMapping("/hello")
	public String hi(@RequestParam String name){
		return helloService.hello(name);
	}
}
