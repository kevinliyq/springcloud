package com.alang.study.springcloud.server_hi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
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
