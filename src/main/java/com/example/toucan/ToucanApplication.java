package com.example.toucan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableJpaRepositories("com.example.toucan.repository")
@EnableTransactionManagement
@EntityScan("com.example.toucan.model.entity")
@ContextConfiguration(classes = {AppConfig.class})
public class ToucanApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToucanApplication.class, args);
    }
}