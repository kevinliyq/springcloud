package com.alang.study.springcloud.serviceribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alang.study.springcloud.serviceribbon.service.HelloService;

@RestController
public class HelloController {

	@Autowired
	private HelloService helloService;

	@GetMapping("/hello/{name}")
	public String sayHelloToService(@PathVariable(name="name") String name){
		
		return helloService.hello(name);
	}
}
