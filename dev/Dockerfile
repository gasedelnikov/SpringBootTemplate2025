FROM openjdk:21
WORKDIR /app
ADD  /core-service-application/target/core-service-application-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
