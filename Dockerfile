FROM maven:3.9.8-eclipse-temurin-21 AS build

RUN mkdir /opt/app

COPY . /opt/app

WORKDIR /opt/app

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

RUN mkdir /opt/app

# Certifique-se de que o nome do JAR está correto
COPY --from=build /opt/app/target/recycle-0.0.1-SNAPSHOT.jar /opt/app/app.jar

WORKDIR /opt/app

ENV PROFILE=dev

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "app.jar"]