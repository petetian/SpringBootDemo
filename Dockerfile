FROM mcr.microsoft.com/java/jdk:11-zulu-ubuntu

LABEL maintainer="pete.tian@microsoft.com"

COPY target/app.jar /app.jar
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]