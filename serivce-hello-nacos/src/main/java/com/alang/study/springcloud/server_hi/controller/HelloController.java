package com.alang.study.springcloud.server_hi.controller;

import com.alang.study.springcloud.server_hi.PropertiesConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private PropertiesConfig config;

	@GetMapping(value = "/hello")
	public String hello(@RequestParam String name) {
		logger.info("Received request hello with param name {}, config {}, username {}, age {}, common name from ext {}, from shared {}", name, config, config.getProfilerName(), config.getThreshold(), config
		.getCommonName(), config.getDebugMode());
		return "hello " + name + " from " + config.getServerPort();
	}

	@RequestMapping(value = "/ticket/buy/{userid}", method = RequestMethod.POST)
	public @ResponseBody String buyTicket(@PathVariable("userId") String userId) {
		return userId + " buy ticket";
	}
}
