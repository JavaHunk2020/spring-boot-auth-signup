#Installing java inside docker container
FROM maven:3.6.3-jdk-11-slim@sha256:68ce1cd457891f48d1e137c7d6a4493f60843e84c9e2634e3df1d3d5b381d36c

#Installing maven inside docker container
#FROM maven:alpine

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