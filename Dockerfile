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
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .   # Copy everything (pom.xml, src/, etc.)
RUN mvn clean package -DskipTests

# Runtime Stage
FROM openjdk:21.0.4-jdk-slim
WORKDIR /app
COPY --from=build /app/target/springbootproject-0.0.1-SNAPSHOT.jar /springbootproject.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/springbootproject.jar"]
