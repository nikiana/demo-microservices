FROM adoptopenjdk/openjdk11:alpine-jre

COPY target/courses-microservice-0.0.1-SNAPSHOT.jar /courses-microservice.jar
ENTRYPOINT ["java", "-jar", "courses-microservice.jar"]