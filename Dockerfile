FROM openjdk:11-jdk-slim
COPY target/mobile-api.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
