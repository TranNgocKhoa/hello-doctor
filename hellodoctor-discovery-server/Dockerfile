FROM openjdk:8u181-jdk-stretch

MAINTAINER Tran Ngoc Khoa <"tranngockhoa.spk@gmail.com">



ADD https://github.com/jwilder/dockerize/releases/download/v0.6.1/dockerize-alpine-linux-amd64-v0.6.1.tar.gz dockerize.tar.gz
RUN tar xzf dockerize.tar.gz
RUN chmod +x dockerize
EXPOSE 8761
COPY maven/hellodoctor-discovery-server-0.0.1-SNAPSHOT.jar hellodoctor-discovery-server.jar
RUN touch /hellodoctor-discovery-server.jar
ENTRYPOINT ["java","-jar","hellodoctor-discovery-server.jar"]