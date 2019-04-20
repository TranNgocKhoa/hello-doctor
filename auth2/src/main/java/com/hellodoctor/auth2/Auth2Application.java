package com.hellodoctor.auth2;

import com.hellodoctor.common.util.JwtUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Auth2Application {

    public static void main(String[] args) {
        SpringApplication.run(Auth2Application.class, args);
    }
    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils();
    }
}
