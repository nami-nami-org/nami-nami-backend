FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/nami_nami-0.0.1.jar
COPY ${JAR_FILE} nami-nami.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "nami-nami.jar"]