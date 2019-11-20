FROM woahbase/alpine-openjdk8
VOLUME /tmp
ADD service-ribbon-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.profiles.active=docker"]
EXPOSE 8765
