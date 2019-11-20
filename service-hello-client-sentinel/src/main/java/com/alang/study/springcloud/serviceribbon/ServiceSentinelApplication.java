package com.alang.study.springcloud.serviceribbon;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient(autoRegister = false)
public class ServiceSentinelApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ServiceSentinelApplication.class, args);
    }
    
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
    	return new RestTemplate();
    }

//    @Bean
//    public SentinelResourceAspect sentinelResourceAspect() {
//        return new SentinelResourceAspect();
//    }

}
