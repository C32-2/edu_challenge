FROM gradle:8.3-jdk17 AS build

WORKDIR /app

COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY gradle ./gradle

COPY src ./src

RUN  chmod +x ./gradlew

RUN ./gradlew clean build -x test --dry-run || true

FROM openjdk:17-jdk-slim

WORKDIR /app

ENV MAX_HEAP_SIZE="-Xmx512m"
ENV MIN_HEAP_SIZE="Xms128m"

COPY --from=build /app/build/libs/*.jar app.jar

CMD ["java", "-jar", "$MAX_HEAP_SIZE", "$MIN_HEAP_SIZE", "app.jar"]