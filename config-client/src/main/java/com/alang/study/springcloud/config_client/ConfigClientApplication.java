package com.alang.study.springcloud.config_client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class ConfigClientApplication implements CommandLineRunner
{
    public static void main( String[] args )
    {
        SpringApplication.run(ConfigClientApplication.class, args);
    }
    
    @Value("${foo}")
    private String foo;
    
    @GetMapping("/hi")
    public String getValue(){
    	return foo;
    }

	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("foo:"+foo);
	}
}
