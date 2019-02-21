1. service uses @EnableEurekaClient to discover and register service into eureka-server.

2. start services with different port
Start application at 8763 port: java -jar xxx.jar --server.port=8763

generate docker image
   mvn clean package docker:build 
   then mydocker/eureka-server-<artifaictId> image will be built
3. eureka-server run command: docker run --name=eureka-server -p 8761:8761 mydocker/eureka-server

   service run command : docker run --link eureka-server:8761 -p 8763:8763 -t mydocker/service-hello
   where:
   -- link eureka-server:8761 means attach to the network eureka-server:8761
   link  will attach a network to the container  
   