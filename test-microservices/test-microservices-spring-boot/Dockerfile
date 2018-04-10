FROM openjdk:8-jdk-alpine
ADD target/test-microservice-spring-boot-0.0.1-SNAPSHOT.jar test.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "test.jar"]