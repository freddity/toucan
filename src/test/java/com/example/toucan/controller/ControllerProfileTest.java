package com.example.toucan.controller;

import com.example.toucan.ToucanApplication;
import com.example.toucan.model.dto.DtoUsernamePassword;
import com.example.toucan.model.entity.EntityUser;
import com.example.toucan.repository.RepositoryNote;
import com.example.toucan.repository.RepositoryUser;
import com.example.toucan.service.ServiceProfile;
import com.example.toucan.service.ServiceSign;
import com.example.toucan.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ControllerProfile.class)
//@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes= ToucanApplication.class)
@AutoConfigureMockMvc
public class ControllerProfileTest {

    @Test
    public void resetPassword_valuesOK_statusOK() throws Exception {

    }

    @Test
    public void deleteAccount_valuesOK_statusOK() throws Exception {

    }

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ServiceProfile serviceProfile;
    @Autowired
    private ApplicationContext applicationContext;
    @MockBean
    private RepositoryNote repositoryNote;
    @MockBean
    private RepositoryUser repositoryUser;

    @BeforeEach
    void printApplicationContext() {
        Arrays.stream(applicationContext.getBeanDefinitionNames())
                .map(name -> applicationContext.getBean(name).getClass().getName())
                .sorted()
                .forEach(System.out::println);
    }
}
