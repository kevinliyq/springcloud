package com.alang.study.springcloud.server_hi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class ServiceHiSpringApplication 
{
    /**
     * @param args
     */
    public static void main( String[] args )
    {
        SpringApplication.run(ServiceHiSpringApplication.class, args);
    }
    
    @Value("${server.port}")
    private String serverPort;
    
    @GetMapping(value="/hello")
    public String hello(@RequestParam String name){
    	return "hello "+name + " from "+ serverPort;
    }
}
