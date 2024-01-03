#Installing java inside docker container
FROM openjdk:11

#Installing maven inside docker container
FROM maven:alpine

WORKDIR  /app
COPY . /app

#this command is running inside app folder
#Creating jar inside the target folder
#app/target
RUN mvn -v
RUN mvn clean install -DskipTests

LABEL maintainer="technohunk444@gmail.com"
#Copying file from target folder to current directory
RUN cp  target/technohunk-car-app-0.0.1-SNAPSHOT.war  technohunk-car-app-0.0.1-SNAPSHOT.war

ENTRYPOINT ["java","-jar","technohunk-car-app-0.0.1-SNAPSHOT.war"] 