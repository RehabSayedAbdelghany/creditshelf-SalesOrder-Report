# Start with a base image containing Java runtime
FROM java:8-jdk-alpine

# Make port 9090 available to the world outside this container
EXPOSE 9090

# The application's jar file
ARG JAR_FILE=target/creditshelf-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} creditshelf.jar


# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/creditshelf.jar"]

