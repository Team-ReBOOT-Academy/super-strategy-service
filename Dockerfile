FROM eclipse-temurin:25-jdk AS builder
WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

COPY src src

RUN chmod +x gradlew && ./gradlew bootJar --no-daemon -x test

FROM eclipse-temurin:25-jre
WORKDIR /app

RUN useradd -u 1001 appuser
USER appuser

COPY --from=builder /app/build/libs/*.jar app.jar

ENV PORT=8080
ENV SERVICE_NAME=super-strategy-service

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]