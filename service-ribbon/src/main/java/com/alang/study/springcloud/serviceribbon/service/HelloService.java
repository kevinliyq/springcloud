package com.alang.study.springcloud.serviceribbon.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HelloService {
	private Logger logger = LoggerFactory.getLogger(HelloService.class);

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired  
    private LoadBalancerClient loadBalancerClient;
	
	@HystrixCommand(fallbackMethod="hiError")
	public String hello(String name){
		ServiceInstance serviceInstance = this.loadBalancerClient.choose("service-hello");
		logger.info("hello client:"+serviceInstance.getHost()+":"+serviceInstance.getPort());
		
		return restTemplate.getForObject("http://SERVICE-HELLO/hello?name="+name, String.class);
	}
	
	public String hiError(String name){
		return "hi " + name + ", service is unavailable";
	}
}
