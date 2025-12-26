# Build stage
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app

COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./

RUN chmod +x gradlew

RUN --mount=type=cache,target=/root/.gradle ./gradlew --no-daemon dependencies

COPY src src

RUN --mount=type=cache,target=/root/.gradle ./gradlew --no-daemon clean bootJar -x test

# Runtime stage
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
