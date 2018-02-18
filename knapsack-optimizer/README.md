# Kapsack Optimizer Service
Kapsack Optimizer Service for submitting problem task, Status enquiry, Get solution details


## Install and Run 
```$git clone https://github.com/aniders/fse-knapsack.git
	$cd fse-knapsack\knapsack-optimizer
	$ mvn clean install  
	$ java -jar target\knapsack-optimizer-${project.version}.jar
```

Knapsack Optimizer service is dependent on knapsack-solver service form providing solutions to the problem. Hence it is recommended to start both the services in local environment, either as independent services or as a composite app in dockerized environment. (Requires Docker to be installed on local) 


## Swagger

``http://localhost:6543/knapsack/swagger-ui.html``


## Security 
Services are protected with basic authentication with in memory user credentials. 
Use following - 

ADMIN:  
``admin : password``
> Admin credentials are requitred for /knapsack/admin/tasks service thats list details of all the submitted task.

USER:  
``user : password``
``user2 : password``

ACTUATOR:    
``actuator : aniders``  
> ACTUATOR can issue /shutdown request  

``$curl -u actuator:aniders -X POST localhost:6543/knapsack/shutdown ``  
``$curl localhost:6543/knapsack/health``

## Dockerfile  
```
FROM openjdk:8
COPY target/knapsack-optimizer-0.0.1.jar /app.jar
EXPOSE 6543/tcp
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=gc", "/app.jar"]
```
Dockerfile to build App image, make sure to user correct build.version.number, and spring profile 

Please refer fse-knapsack/README.md for instructions on how to use this Dockerfile to start service as composite app. 


## Notes 
Project lombok is used, Install it inside your editor (Eclipse). 

