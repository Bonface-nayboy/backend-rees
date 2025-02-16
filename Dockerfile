# # FROM maven 3.9.9-openjdk-21 AS build
# FROM maven:3.9.9-eclipse-temurin-21 AS build

# COPY . .
# RUN mvn clean package -DskipTests

# FROM openjdk:21.0.4-jdk-slim
# # COPY --FROM=build /target/springbootproject-0.0.1-SNAPSHOT.jar springbootproject.jar
# COPY --from=build /target/springbootproject-0.0.1-SNAPSHOT.jar /springbootproject.jar

# EXPOSE 8080
# ENTRYPOINT ["java" ,"-jar" ,"springbootproject.jar"]

# Build Stage
# FROM maven:3.9.9-eclipse-temurin-21 AS build
# WORKDIR /app
# COPY . .   # Copy everything (pom.xml, src/, etc.)
# RUN mvn clean package -DskipTests

# # Runtime Stage
# FROM openjdk:21-jdk-slim
# WORKDIR /app
# COPY --from=build /app/target/springbootproject-0.0.1-SNAPSHOT.jar /springbootproject.jar
# EXPOSE 8080
# ENTRYPOINT ["java", "-jar", "/springbootproject.jar"]



# FROM maven:3.8.5-openjdk-17 AS build
# COPY . .   # Copy everything (pom.xml, src/, etc.)
# RUN mvn clean package -DskipTests

# # Runtime Stage
# FROM openjdk:17.0.1-jdk-slim
# COPY --from=build /app/target/springbootproject-0.0.1-SNAPSHOT.jar springbootproject.jar
# EXPOSE 8080
# ENTRYPOINT ["java", "-jar", "/springbootproject.jar"]



# Build Stage
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime Stage
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/springbootproject-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

