package com.alang.study.springcloud.server_hi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alang.study.springcloud.server_hi.ServiceHelloSpringApplication;

@RestController
@RefreshScope
public class HelloController {

	private Logger logger = LoggerFactory.getLogger(ServiceHelloSpringApplication.class);

	@Value("${server.port}")
	private String serverPort;

	@Value("${external.config:}")
	private String config;

	@Value("${profile.name:}")
	private String profilerName;

	@Value("${age:0}")
	private int age;

	@GetMapping(value = "/hello")
	public String hello(@RequestParam String name) {
		logger.info("Received request hello with param name {}, config {}, username {}, age {}", name, config, profilerName, age);
		return "hello " + name + " from " + serverPort;
	}

	@RequestMapping(value = "/ticket/buy/{userid}", method = RequestMethod.POST)
	public @ResponseBody String buyTicket(@PathVariable("userId") String userId) {
		return userId + " buy ticket";
	}
}
