package com.example.toucan;

import com.example.toucan.security.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class ToucanApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToucanApplication.class, args);
    }
}
