FROM maven:3.3-jdk-8-onbuild

COPY target/knapsack-solver-0.0.1.jar /app.jar
EXPOSE 8080/tcp
ENTRYPOINT ["java", "-jar", "/app.jar"]