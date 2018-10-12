package com.alang.study.springcloud.servicefeign;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

//@SpringCloudApplication
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
public class ServiceFeignApplication implements CommandLineRunner
{
    public static void main( String[] args )
    {
        SpringApplication.run(ServiceFeignApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		System.out.println(Arrays.asList(args));
	}
    
}
