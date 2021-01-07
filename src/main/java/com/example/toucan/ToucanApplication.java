package com.example.toucan;

import com.example.toucan.model.dto.DtoNote;
import com.example.toucan.model.dto.DtoUsernamePassword;
import com.example.toucan.model.entity.EntityNote;
import com.example.toucan.repository.RepositoryNote;
import com.example.toucan.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

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