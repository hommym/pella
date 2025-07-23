# Stage 1: build the JAR
FROM gradle:8.14-jdk17 AS builder
WORKDIR /app

# Copy only what's needed for gradle to cache dependencies
COPY build.gradle settings.gradle ./
COPY gradlew gradlew
COPY gradle ./gradle
RUN gradle --no-daemon build -x test

# Now copy the rest of the source and build
COPY src ./src
RUN gradle --no-daemon bootJar -x test

# Stage 2: run the app
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the fat JAR from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the port Spring Boot uses (default 8080)
EXPOSE 8000

# Launch!
ENTRYPOINT ["java","-jar","app.jar"]
