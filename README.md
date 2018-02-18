# fse-knapsack
Knapsack Optimizer Service






##Running on Google Compute Engine instance (Container-Optimized OS, no pre-installed java / mvn. comes with Docker container runtime) 

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
kompose is a convenience tool to go from local Docker development to managing your application with Kubernetes.   
``curl -L https://github.com/kubernetes/kompose/releases/download/v1.9.0/kompose-linux-amd64 -o kompose ``  
``chmod +x kompose``   
``sudo mv ./kompose /usr/local/bin/kompose ``  


4. Build image   
``docker-compose build ``  


5. From /fse-knapsack directory, run  ``kompose convert -f docker-compose.yml``  


6. start composite apps   
``kompose up  ``  