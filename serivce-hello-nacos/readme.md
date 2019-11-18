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
```

* use namespaces
```text
配置这设置在自己的configuration file中
spring.cloud.nacos.config.namespace=<namespace_id>
```


### Use as discovery center
1. 在pom.xml配置
```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
```
2. 在SpringBootApplication启动类中@EnableDiscoveryClient