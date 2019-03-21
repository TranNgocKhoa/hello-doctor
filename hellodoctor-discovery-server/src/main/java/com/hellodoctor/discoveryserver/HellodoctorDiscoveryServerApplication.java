package com.hellodoctor.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class HellodoctorDiscoveryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HellodoctorDiscoveryServerApplication.class, args);
    }

}
