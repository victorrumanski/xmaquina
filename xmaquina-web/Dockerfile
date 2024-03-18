# First stage: build the application
FROM maven:3.8.3-jdk-17 AS build
COPY . /app
WORKDIR /app
RUN mvn package -DskipTests

# Second stage: create a slim image
FROM openjdk:17-jdk-slim
COPY --from=build /app/target/xmaquina-web-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]