package com.alang.study.springcloud.servicefeign;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringCloudApplication
@EnableFeignClients
public class ServiceFeignApplication 
{
    public static void main( String[] args )
    {
        SpringApplication.run(ServiceFeignApplication.class, args);
    }
    
}
