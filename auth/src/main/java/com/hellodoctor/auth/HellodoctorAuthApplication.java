package com.hellodoctor.auth;

import com.hellodoctor.common.util.JwtUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class HellodoctorAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(HellodoctorAuthApplication.class, args);
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils();
    }
}
