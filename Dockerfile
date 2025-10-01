# 1️⃣ Etapa de compilación
FROM maven AS build
WORKDIR /app

# Copiar archivos de proyecto y compilar
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# 2️⃣ Etapa de ejecución
FROM openjdk:21-slim
WORKDIR /app

# Copiar el JAR generado en la etapa anterior
COPY --from=build /app/target/nami_nami-0.0.1.jar nami-nami.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "nami-nami.jar"]
