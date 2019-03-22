FROM openjdk:8-jre-alpine

MAINTAINER Tran Ngoc Khoa <"tranngockhoa.spk@gmail.com">

ADD https://github.com/jwilder/dockerize/releases/download/v0.6.1/dockerize-alpine-linux-amd64-v0.6.1.tar.gz dockerize.tar.gz
RUN tar xzf dockerize.tar.gz
RUN chmod +x dockerize
EXPOSE 8080
COPY maven/hellodoctor-api-gateway-0.0.1-SNAPSHOT.jar hellodoctor-api-gateway.jar
RUN touch /hellodoctor-api-gateway.jar
ENTRYPOINT ["java","-jar","hellodoctor-api-gateway.jar"]