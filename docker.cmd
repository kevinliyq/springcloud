1. create a container and start
docker container run \
  -d \
  --rm \
  --name wordpressdb \
  --volume "$PWD/":/var/www/html \
  --env MYSQL_ROOT_PASSWORD=123456 \
  --env MYSQL_DATABASE=wordpress \
  mysql:5.7
where 
-d means run at background
--rm means delete the container after stop
--name means naming the container
--volume means mapping {host} foler to {container}

2. start eureka-server:
docker run -d --name=eureka-server -p 8761:8761 mydocker/eureka-server

3. start service-hello:
docker run -d --name=service-hello --link eureka-server:8761 -p 8762:8762 -t mydocker/service-hello
docker run -d --name=service-hello2 --link eureka-server:8761 -p 8763:8762 -t mydocker/service-hello
where --link link to <container_name>:port

4. start service-feign
docker run -d --name=service-feign --link eureka-server:8761 -p 8765:8765 -t mydocker/service-feign
