FROM openjdk:11

RUN apt-get update && apt-get install -y maven
RUN mkdir -p /usr/src/app
COPY /target/fullsurvey-1.0.0.jar /usr/src/app
WORKDIR /usr/src/app/

#run the spring boot application
ENTRYPOINT ["java","-Dserver.port=8080", "-Dspring.profiles.active=dev", "-jar","/usr/src/app/fullsurvey-1.0.0.jar"]