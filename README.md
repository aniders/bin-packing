# bin-packing
Knapsack Optimizer Service



## Project structure 
```
 .
    ├── knapsack-optimizer           # /kanpsack service build using spring-boot (/knapsack)
    ├── knapsack-solver              # /solver A solver implementation for knapsack problems
    ├── docker-compose.yml           # commands to build run both /kanpsack and /solver together in isolated environment
    ├── README.md                    # Project related information here. 
    └── Datasets.md                  # Datasets to benchmark Optimizer 
```


## knapsack-optimizer - 
Runs on `port 6543` and context path `/kanpsack ` supports following endpoints 

* POST `/knapsack/tasks`  
content: `application/json` with knapsack problem specification  
output: `json` with task status   

* GET `/knapsack/tasks/<id>`  
output: `json` with task status (id, status, timestamp of last status update)   

* GET `/knapsack/solutions/<id>`    
output: `json` with solution details   
 
* GET `/knapsack/admin/tasks`      
output: `json` with a list of submitted, running, and completed tasks   

* POST `/knapsack/shutdown`   

* GET `/knapsack/status`  

``Browse swagger UI to find detailed API documentation [http://localhost:6543/knapsack/swagger-ui.html](http://localhost:6543/knapsack/swagger-ui.html)``

## knapsack-solver
A rest service used by `knapsack-optimizer` to perform task. This service is being called in Async mode. 
This micro-service runs along with optimizer inside Docker container, and is accessible by optimizer using service name.


## Running on Google Compute Engine instance (Container-Optimized OS, no pre-installed java / mvn. comes with Docker container runtime) 

1. Create instance and connect using cloud shell. 

2. Clone 'fse-knapsack' project from github   ``git clone https://github.com/aniders/fse-knapsack.git``


3. From project base directory run -    
``docker run -it --rm -w /opt/maven -v $PWD:/opt/maven -v $HOME/.m2:/root/.m2  maven:3.3-jdk-8 mvn clean install ``


4. docker-compose up   
``docker run -v /var/run/docker.sock:/var/run/docker.sock -v "$PWD:/rootfs/$PWD" -w="/rootfs/$PWD" docker/compose:1.13.0 up``  
This will start both applications. /knapsack service on port 6543 and /solver on 8080 


## Running in Kubernetes clusters  

1. Create cluster and connect using cloud shell. 


2. Clone 'fse-knapsack' project from github   ``git clone https://github.com/aniders/fse-knapsack.git``


3. Install kompose   
kompose is a convenience tool to go from local Docker development to managing application with Kubernetes.   
``curl -L https://github.com/kubernetes/kompose/releases/download/v1.9.0/kompose-linux-amd64 -o kompose ``  
``chmod +x kompose``   
``sudo mv ./kompose /usr/local/bin/kompose ``  


4. Build image   
``docker-compose build ``  


5. From /fse-knapsack directory, run  ``kompose convert -f docker-compose.yml``  


6. start composite apps   
`kompose up  `  


7. Service starts on port 6543 





# Applicating URL (current deployment on Cloud)
`Services are deployed on Google Cloud platform, Kubernetes Engine   `    
> http://35.202.54.163:6543/knapsack/swagger-ui.html 

`See /kanpsack-optimizer/README.md for user credentials to access deployed services. `

## Knapsack App http://35.226.203.140:4200  
Displays list of submitted Tasks and solution details. 

 
