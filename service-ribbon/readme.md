1. add spring-cloud-starter-ribbon to enable ribbon for load balance
	@EnableDiscoveryClient to register service into eureka
2. add @LoadBalanced for RestTemplate
	@Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
    	return new RestTemplate();
    }
 3. by default, ribbon take round robbin to select available service for serve request
    and has no retry mechanism
 4. enable retry has to add following artifact:
    check with http://localhost:{server.port}/autoconfig to check whether satisfy RibbonAutoConfiguration.
    4.a:
 	<dependency>
    		<groupId>org.springframework.retry</groupId>
    		<artifactId>spring-retry</artifactId>
	</dependency>
	
	4.b: and add a Bean for load balance in @Configuration
	@Bean
@ConditionalOnClass(name = "org.springframework.retry.support.RetryTemplate")
public LoadBalancedRetryPolicyFactory loadBalancedRetryPolicyFactory(SpringClientFactory clientFactory) {
    return new RibbonLoadBalancedRetryPolicyFactory(clientFactory);
}

5. Support hystrix-dashboard