
FROM maven:3.9-eclipse-temurin-25 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM eclipse-temurin:25-jre
LABEL maintainer="pete.tian@microsoft.com"

COPY --from=build /home/app/target/app.jar /usr/local/bin/app.jar
EXPOSE 8080

RUN useradd -u 8877 nonroot
USER nonroot

CMD ["java", "-jar", "/usr/local/bin/app.jar"]