FROM ubuntu:latest AS build
LABEL authors="yousof"

RUN apt-get update
RUN apt-get install -y maven openjdk-17-jdk

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /target/gpa-genius-0.0.1-SNAPSHOT.war app.war

ENTRYPOINT ["java", "-jar", "app.jar"]