# Use an official OpenJDK base image
FROM eclipse-temurin:17-jdk

# Set the working directory
WORKDIR /app

# Copy Maven wrapper & project files
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Preload dependencies (optional, speeds up build)
RUN ./mvnw dependency:go-offline

# Copy the rest of the project
COPY src ./src

# Build the project
RUN ./mvnw package -DskipTests

# Expose the default Spring Boot port
EXPOSE 8080

# Run the Spring Boot app
CMD ["java", "-jar", "target/PokemonCardServer-0.0.1-SNAPSHOT.jar"]
