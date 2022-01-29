FROM adoptopenjdk/openjdk11:alpine-jre

COPY /target/admin-microservice-0.0.1-SNAPSHOT.jar admin-microservice.jar
ENTRYPOINT ["java", "-jar", "admin-microservice.jar"]