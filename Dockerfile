FROM openjdk:17
EXPOSE 8080
ADD target/Picture-of-the-day-from-NASA-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]