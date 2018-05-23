1. service uses @EnableEurekaClient to discover and register service into eureka-server.

2. generate docker image
   mvn clean package docker:build 
   then mydocker/eureka-server-<artifaictId> image will be built
3. eureka-server run command: docker run --name=eureka-server -p 8761:8761 mydocker/eureka-server

   service run command : docker run --link eureka-server:8761 -p 8763:8763 -t mydocker/service-hello
   link  will attach a network to the container  
   