# Use OpenJDK 21 as base image
FROM eclipse-temurin:21-jdk

# Set the working directory
WORKDIR /app

# Copy the Maven build file and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the rest of the application files
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/springbootproject-0.0.1-SNAPSHOT.jar"]
