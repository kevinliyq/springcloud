package com.alang.study.springcloud.server_hi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceHelloSpringApplication 
{
	 /**
     * @param args
     */
    public static void main( String[] args )
    {
        SpringApplication.run(ServiceHelloSpringApplication.class, args);
    }
    
    
}
