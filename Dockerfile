

FROM openjdk:17-jdk-slim as build

#Information around who maintains the image
MAINTAINER himanshu-nayal

# Add the application's jar to the container and copy that to your docker environment
COPY target/UserService-0.0.1-SNAPSHOT.jar UserService-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","UserService-0.0.1-SNAPSHOT.jar"]