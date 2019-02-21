package com.alang.study.springcloud.eurekaserver;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaSpringApplication implements CommandLineRunner
{
    public static void main( String[] args )
    {
    	SpringApplication.run(EurekaSpringApplication.class, args);
    }
    
    @Override
	public void run(String... args) throws Exception {
		System.out.println(Arrays.asList(args));
	}
} 
