FROM adoptopenjdk/openjdk11:alpine-jre

COPY /target/auth-microservice-0.0.1-SNAPSHOT.jar /auth-microservice.jar
ENTRYPOINT ["java", "-jar", "auth-microservice.jar"]