FROM openjdk:8
COPY target/knapsack-optimizer-0.0.1.jar /app.jar
EXPOSE 6543/tcp
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=gc", "/app.jar"]