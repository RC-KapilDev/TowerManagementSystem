# Use the OpenJDK 17 base image with Alpine Linux
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the target directory into the container
COPY target/*.jar app.jar

# Expose the port that your application will run on
EXPOSE 8761

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
