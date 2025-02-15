# Use Eclipse Temurin JDK as the base image
FROM eclipse-temurin:21-jdk

# Set the working directory
WORKDIR /app

# Install Maven
RUN apt-get update && apt-get install -y maven

# Copy the Maven build file
COPY pom.xml .

# Download dependencies for offline usage
RUN mvn dependency:go-offline -B

# Copy the rest of the application files
COPY . .

# Build the application
RUN mvn package -DskipTests

# Expose the application port
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "target/your-app.jar"]
