FROM openjdk:8u181-jdk-stretch

MAINTAINER Tran Ngoc Khoa <"tranngockhoa.spk@gmail.com">

ADD https://github.com/jwilder/dockerize/releases/download/v0.6.1/dockerize-alpine-linux-amd64-v0.6.1.tar.gz dockerize.tar.gz
RUN tar xzf dockerize.tar.gz
RUN chmod +x dockerize
EXPOSE 8082
COPY maven/hellodoctor-test-service-0.0.1-SNAPSHOT.jar hellodoctor-test-service.jar
RUN touch /hellodoctor-test-service.jar
ENTRYPOINT ["java","-jar","hellodoctor-test-service.jar"]