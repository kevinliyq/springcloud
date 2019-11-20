package com.alang.study.springcloud.serviceribbon.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {
	private Logger logger = LoggerFactory.getLogger(HelloService.class);

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired  
    private LoadBalancerClient loadBalancerClient;

	public String hello(String name){
		return loadBalaner(name);
	}

	private String loadBalaner(String name) {
		String url = "http://" + "service-hello-nacos" + "/hello?name=" + name;
		return restTemplate.getForObject(url, String.class);
	}

	private String loadBalanerWithFQDN(String name) {
		ServiceInstance serviceInstance = this.loadBalancerClient.choose("service-hello-nacos");
		logger.info("hello client:"+serviceInstance.getHost()+":"+serviceInstance.getPort());

		String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/hello?name=" + name;

		return new RestTemplate().getForObject(url, String.class);
	}
}
