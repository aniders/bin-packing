FROM maven:3.3-jdk-8-onbuild

COPY target/knapsack-optimizer-0.0.1.jar /app.jar
EXPOSE 6543/tcp
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=gc", "/app.jar"]