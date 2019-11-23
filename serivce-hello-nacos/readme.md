# Integrate nacos as config server and discovery center
### Use nacos as config server
1. Integer nacos as config server
```html
https://github.com/alibaba/spring-cloud-alibaba/blob/master/spring-cloud-alibaba-examples/nacos-example/nacos-config-example/readme.md
```

```java
<dependency>
   <groupId>org.springframework.cloud</groupId>
   <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
</dependency>
```

2. There are three ways to get parameters from nacos 
* uses default group then needs to define 
```properties
dataId:
${prefix}-${spring.profile.active}.${file-extension}
${prefix} is spring.application.name by default
${spring.profile.active} is profile name
${file-extension} is properties by default

group: DEFAULT_GROUP
``` 
* use different group
 ```text
 Set DataId, i.e. service-hello-nacos.properties
 Set Group in nacos, i.e. DEV_GROUP
 
In your properties or yaml define this item
spring.cloud.nacos.config.group=DEV_GROUP
#使用bootstrap, 则需要在bootstrap里定义group，另外可以--spring.cloud.nacos.config.group=DEV_GROUP来修改

如果设置spring.profiles.active，则会存在两个dataId
<spring.application.name>.properties和spring.application.name-<profile>.properties
groupId为指定的，未指定则为DEFAULT_GROUP

对于支持多个动态配置文件
参见bootstrap.properties配置文件

```

* use namespaces
```text
配置这设置在自己的configuration file中
spring.cloud.nacos.config.namespace=<namespace_id>
```

3. Nacos Config 原理
Nacos服务器支持动态配置，
* 可以根据dataId, group和namespace配置内容，如果需要持久话，需要配置mysql作为nacos数据源，否则nacos重启后配置会消失
* nacos客户端可以根据dataId和group获取同步配置内容，当时客户端会以一个长连接（默认30秒）从nacos server pull. 
 - org.springframework.cloud.bootstrap.BootstrapConfiguration
 - NacosPropertySourceLocator可以load自定义获取的数据源, 如从Nacos
 - NacosFactory createConfig
 - new NacosConfigService
 - start a HttpServerAgent to get serverAddr of nacos
 - Start a client worker with a single thread for long polling toward nacos server

### Use as discovery center
1. 在pom.xml配置
```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
```
2. 在SpringBootApplication启动类中@EnableDiscoveryClient