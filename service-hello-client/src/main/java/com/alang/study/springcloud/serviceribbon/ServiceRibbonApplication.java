package com.alang.study.springcloud.serviceribbon;

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
public class ServiceRibbonApplication 
{
    public static void main( String[] args )
    {
        SpringApplication.run(ServiceRibbonApplication.class, args);
    }
    
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
    	return new RestTemplate();
    }
    
//    @Bean
//    @ConditionalOnClass(name = "org.springframework.retry.support.RetryTemplate")
//    public LoadBalancedRetryPolicyFactory loadBalancedRetryPolicyFactory(SpringClientFactory clientFactory) {
//        return new RibbonLoadBalancedRetryPolicyFactory(clientFactory);
//    }
//
//    @Bean
//    public IRule ribbonRule() {
//        return new AvailabilityFilteringRule();
//    }
}
