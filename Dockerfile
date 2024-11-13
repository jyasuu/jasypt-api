# Build stage (maven-based approach)
FROM openjdk:17-jdk-slim AS build

# Set the working directory for the build stage
WORKDIR /build

# Copy the pom.xml and source code (for caching dependencies)
COPY pom.xml .
COPY mvnw .
COPY src ./src
COPY wrapper ./.mvn/wrapper

# Package the application (build the JAR file)
RUN ./mvnw clean package -DskipTests

# Runtime stage
FROM openjdk:17-jdk-slim AS runtime

# Set the working directory for the runtime stage
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /build/target/jasypt-api-0.0.1-SNAPSHOT.jar /app/jasypt-api.jar

# Expose the application port (default Spring Boot port is 8080)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/jasypt-api.jar"]
