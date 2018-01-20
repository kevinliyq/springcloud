1. Feign is a fake HTTP client, which can use JAX-RS Annotation to call a remove HTTP service
	@SpringCloudApplication will register service and discover clients 
2. Feign takes along with Ribbon for load balance and circuit breaker.
	
3. in SpringCloud D version, the circuit break is not enabled by default. Thus needs to explicitly to enable it
feign.hystrix.enabled=true

4. feign takes along retry mechanism even without spring retry dependency
   see codes:
   @Bean
@Primary
@ConditionalOnMissingClass("org.springframework.retry.support.RetryTemplate")
public CachingSpringLoadBalancerFactory cachingLBClientFactory(
        SpringClientFactory factory) {
    return new CachingSpringLoadBalancerFactory(factory);
}

@Bean
@Primary
@ConditionalOnClass(name = "org.springframework.retry.support.RetryTemplate")
public CachingSpringLoadBalancerFactory retryabeCachingLBClientFactory(
        SpringClientFactory factory, LoadBalancedRetryPolicyFactory retryPolicyFactory) {
    return new CachingSpringLoadBalancerFactory(factory, retryPolicyFactory, true);
}
