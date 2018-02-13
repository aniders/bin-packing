# Kapsack Optimizer Service
Kapsack Optimizer Service for submitting problem task, Status enquiry, Get solution details


```
$ mvn clean install  
$ java -jar target\ knapsack-optimizer-${project.version}.jar
```

## Swagger

``http://localhost:8080/knapsack/swagger-ui.html``


## Security 
Create/Update/Delete services are protected with basic authentication with in memory user credentials. 
Use following - 

``user : password``

``user2@test.com : password``

``user3@test.com : password``


## Notes 
Project lombok is used, Install it inside your editor (Eclipse). 
