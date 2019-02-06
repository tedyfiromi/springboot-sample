FROM openjdk:8-alpine

MAINTAINER Tedy Firomi

RUN apk update && apk add bash

RUN mkdir -p /opt/app

ENV PROJECT_HOME /opt/app

COPY target/springboot-sample.jar $PROJECT_HOME/springboot-sample.jar

WORKDIR $PROJECT_HOME

CMD ["java", "-jar", "-Dspring.profiles.active=prod" ,"./springboot-sample.jar"]