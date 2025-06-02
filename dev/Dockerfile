FROM openjdk:17
MAINTAINER Grigoriy Sedelnikov
WORKDIR /app
RUN mkdir -p /app/dev/uploadFiles
RUN chmod 755 /app/dev/uploadFiles
ADD ../core-service-application/target/core-service-application-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
