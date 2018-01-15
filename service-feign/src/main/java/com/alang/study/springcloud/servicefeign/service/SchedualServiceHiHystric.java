package com.alang.study.springcloud.servicefeign.service;

import org.springframework.stereotype.Component;

@Component
public class SchedualServiceHiHystric implements SchedualServiceHello{

	@Override
	public String hello(String name) {
		return "sorry " + name;
	}

}
