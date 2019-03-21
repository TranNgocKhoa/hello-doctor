FROM openjdk:8u181-jdk-stretch

MAINTAINER Tran Ngoc Khoa <"tranngockhoa.spk@gmail.com">

ADD https://github.com/jwilder/dockerize/releases/download/v0.6.1/dockerize-alpine-linux-amd64-v0.6.1.tar.gz dockerize.tar.gz
RUN tar xzf dockerize.tar.gz
RUN chmod +x dockerize
EXPOSE 8888
COPY maven/hellodoctor-configserver-0.0.1-SNAPSHOT.jar hellodoctor-configserver.jar
RUN touch /hellodoctor-configserver.jar
ENTRYPOINT ["java","-jar","hellodoctor-configserver.jar"]