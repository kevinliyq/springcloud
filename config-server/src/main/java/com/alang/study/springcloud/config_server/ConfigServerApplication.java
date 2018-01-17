package com.alang.study.springcloud.config_server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * http request and config file mapping
 * {application}/{profile}[/{label}]
 * /{application}-{profile}.yml
 * /{label}/{application}-{profile}.yml
 * /{application}-{profile}.properties
 * /{label}/{application}-{profile}.properties
 * @author macbook
 *
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication implements CommandLineRunner
{
    public static void main( String[] args )
    {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

    @Value("${spring.cloud.config.server.git.searchPaths}")
    private String searchPaths;
    
    @Value("${foo:Hello world}")
    private String foo;
    
    
	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("searchPaths:"+searchPaths+",foo="+foo);	
	}
}
