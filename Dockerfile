FROM maven AS build
WORKDIR /app

# Copiar archivos de proyecto y compilar
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Eclipse Temurin como base (Java 21)
FROM eclipse-temurin:21-jdk AS exec
WORKDIR /app

# Copiar el JAR generado
COPY --from=build /app/target/nami_nami-0.0.1.jar nami-nami.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "nami-nami.jar"]
