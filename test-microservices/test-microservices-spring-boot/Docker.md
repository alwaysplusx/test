### use maven to package and spring-boot repackage as spring-boot application

mvn package spring-boot:repackage

### build docker image named test/docker-spring-boot

docker build -t test/docker-spring-boot .

### run as container

docker run -it -p 8080:8080 test/docker-spring-boot