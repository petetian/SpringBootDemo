
FROM maven:3.8.7-openjdk-18-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:11-jre-slim
LABEL maintainer="pete.tian@microsoft.com"

COPY --from=build /home/app/target/app.jar /app.jar
EXPOSE 8080

RUN useradd -u 8877 nonroot
USER nonroot

CMD ["java", "-jar", "/app.jar"]