# Use Java 17 base image
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy all project files
COPY . .

# Give Maven wrapper permission to execute
RUN chmod +x mvnw

# Build the Spring Boot jar
RUN ./mvnw clean package -DskipTests

# Expose port
EXPOSE 8080

# Run the jar
CMD ["java", "-jar", "target/api-0.0.1-SNAPSHOT.jar"]