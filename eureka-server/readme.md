What Eureka is:
Eureka是Netflix开发的服务发现框架，本身是一个基于REST的服务，主要用于定位运行在AWS域中的中间层服务，以达到负载均衡和中间层服务故障转移的目的。SpringCloud将它集成在其子项目spring-cloud-netflix中实现SpringCloud的服务发现功能。

Eureka 提供两个基本功能：
1. 服务注册
2. 服务检索

Eureka 包含eureka-server and eureka-client
Eureka client will register service into eureka-server, eureka-server save information of available service,which can be observed on web UI.
Eureka-client embeded a round-robin (轮询负载）load balancer. 

After eureka-client is started, it will send heart-beat with 30 seconds periodly. if eureka-server can't register heart message of service, then it will remove the service by default in 90 seconds.

eureka-client cache these discovered service, even eureka-server is down, eureka-client can get service and communicate without eureka-server. 

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

5. set HA for eureka-server
   - add following ip/hostname into /etc/hosts
   127.0.0.1 eureka1
   127.0.0.1 eureka2
   after set with different hostname than it can present on DS Replicas
   using --spring.profiles.active=<profile_name> to activate profile
   a. start eureka-server1
   	java -jar target/eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=eureka1
   b. eureka-server2
    java -jar target/eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=eureka2
    
6. generate docker image
   mvn clean package docker:build 
   then mydocker/eureka-server-<artifaictId> image will be built
7. eureka-server run command: 
docker run --name=eureka-server -p 8761:8761 mydocker/eureka-server

   service run command : docker run --link eureka-server:8761 -p 8763:8763 -t mydocker/service-hello
   link  will attach a network to the container  
 
 8. uses docker-compose can be used to manage multiple containers. 
 can build image and manage start/stop container.
    docker-compose -f <*.yml> up|stop
    http://www.ruanyifeng.com/blog/2018/02/docker-wordpress-tutorial.html 
   