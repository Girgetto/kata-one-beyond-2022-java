FROM maven:3-jdk-11 AS builder

COPY . /app/

WORKDIR /app

RUN mvn clean package

FROM openjdk:20-slim-buster AS final

COPY --from=builder /app/target/kata-one-beyond-2022-java-*-jar-with-dependencies.jar /app.jar

ENTRYPOINT ["java", "-jar", "-Dorg.slf4j.simpleLogger.defaultLogLevel=error", "/app.jar"]
