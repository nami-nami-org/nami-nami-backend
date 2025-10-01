FROM maven:3.9.2-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
WORKDIR /app

COPY --from=build /app/target/nami_nami-0.0.1.jar nami-nami.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "nami-nami.jar"]
