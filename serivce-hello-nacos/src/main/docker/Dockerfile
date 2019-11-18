FROM woahbase/alpine-openjdk8
VOLUME /tmp
ADD service-hello-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dlog.dir=/working/logs","-jar","/app.jar","--spring.profiles.active=docker"]
EXPOSE 8762
