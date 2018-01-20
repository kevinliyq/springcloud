What Eureka is:
Eureka是Netflix开发的服务发现框架，本身是一个基于REST的服务，主要用于定位运行在AWS域中的中间层服务，以达到负载均衡和中间层服务故障转移的目的。SpringCloud将它集成在其子项目spring-cloud-netflix中实现SpringCloud的服务发现功能。

Eureka 提供两个基本功能：
1. 服务注册
2. 服务检索

Eureka 包含eureka-server and eureka-client
Eureka client will register service into eureka-server, eureka-server save information of available service,which can be observed on web UI.
Eureka-client embeded a round-robin (轮询负载）load balancer. 

After eureka-client is started, it will send heart-beat with 30 seconds periodly. if eureka-server can't register heart message of service, then it will remove the service by default in 90 seconds. 

在SpringCloud应用程序中可以通过一下方式引入eureka-server
   a. Add spring-cloud-starter-eureka-server into pom.xml
   <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka-server</artifactId>
        </dependency>
   b. Add @EnableEurekaServe to enable eureka server
   
   c. configure parameters 
   	  eureka.instance.hostname=<hostname|ip|fqdn>
  	  eureka.client.registerWithEureka=false
  	  eureka.client.fetchRegistry=false
  	  eureka.client.serviceUrl.defaultZone=http://${eureka.instance.hostname}:${server.port}/eureka/
  	  
  	  please note as eureka-server has to set registerWithEureka and fetchRegistry to false. 
  	  - with registerWithEureka = false to indicate don't need to register itself
  	  - with fetchRegistry = false means don't need to discover services 
  	  - eureka.client.serviceUrl.defaultZone 这是服务注册的地址，高可用服务注册中心，你会看到这个属性多个地址是用英文逗号分割的
