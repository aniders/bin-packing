FROM openjdk:8
COPY target/knapsack-optimizer-0.0.1.jar /app.jar
EXPOSE 9091/tcp
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=gc", "/app.jar"]