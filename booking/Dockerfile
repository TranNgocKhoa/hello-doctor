FROM openjdk:8-jre-alpine

MAINTAINER Tran Ngoc Khoa <"tranngockhoa.spk@gmail.com">

ADD https://github.com/jwilder/dockerize/releases/download/v0.6.1/dockerize-alpine-linux-amd64-v0.6.1.tar.gz dockerize.tar.gz
RUN tar xzf dockerize.tar.gz
RUN chmod +x dockerize
EXPOSE 8083
COPY maven/hellodoctor-booking-service-0.0.1-SNAPSHOT.jar hellodoctor-booking-service.jar
RUN touch /hellodoctor-booking-service.jar
ENTRYPOINT ["java","-jar","hellodoctor-booking-service.jar"]