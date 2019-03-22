# hello-doctor
 
 ## How to import this project to IntelliJ IDEA
 
 I don't know how to do in other IDE but in IntelliJ IDEA, you could do following steops:
 
 1. Open project's folder
 2. On **Maven Toolbox**, click **(+)** button *(All Maven Projects)* and then choose `pom.xml` files.
 3. Waiting for IDE processing.
 
 ## How to deploy services on Docker
 
 1. Execute file **buildImages.bat** by `$ ./buildImages.bat`
 2. Execute `$ docker-compose up -d`
 
 # How to run services on local
 
 Updating...
 
 
## How to test API

Test Service is deployed on `localhost:8082`. So far, there is only one API is: http://localhost:8080/api/test/message

To access the API through **Gateway**, try http://localhost:8080/api/test/message

**Eureka** http://localhost:8761/

**Config Server** http://localhost:8888/discovery-server/default



## Thanks to

https://github.com/spring-petclinic

https://github.com/numery009
