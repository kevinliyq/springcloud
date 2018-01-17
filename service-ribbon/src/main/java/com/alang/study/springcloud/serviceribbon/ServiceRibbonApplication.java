package com.alang.study.springcloud.serviceribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancedRetryPolicyFactory;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancedRetryPolicyFactory;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IRule;

/**
 * 1. Support loadbalance at client part
 * 2. Support hystrix for circuit breaker
 * @author macbook
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
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
    
    @Bean
    @ConditionalOnClass(name = "org.springframework.retry.support.RetryTemplate")
    public LoadBalancedRetryPolicyFactory loadBalancedRetryPolicyFactory(SpringClientFactory clientFactory) {
        return new RibbonLoadBalancedRetryPolicyFactory(clientFactory);
    }
    
//    @Bean
//    public IRule ribbonRule() {
//        return new AvailabilityFilteringRule();
//    }
}
