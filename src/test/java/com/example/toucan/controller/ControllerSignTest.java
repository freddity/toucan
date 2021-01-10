package com.example.toucan.controller;

import com.example.toucan.ToucanApplication;
import com.example.toucan.model.dto.DtoUsernamePassword;
import com.example.toucan.model.entity.EntityUser;
import com.example.toucan.repository.RepositoryNote;
import com.example.toucan.repository.RepositoryUser;
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
@WebMvcTest(controllers = ControllerSign.class)
//@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes= ToucanApplication.class)
@AutoConfigureMockMvc
public class ControllerSignTest {

    @Test
    public void takeToken_valuesOK_statusOK() throws Exception {
        JwtUtil jwtUtil = new JwtUtil();
        String token = jwtUtil.generateToken(new EntityUser("user1", "user1"));

        when(serviceSign.takeToken(eq("user1"), eq("user1")))
                .thenReturn(token);

        mockMvc.perform(
                post("/signin")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(new DtoUsernamePassword("user1", "user1")))
        ).andExpect(status().isOk()).andExpect(content().string(token));
    }

    @Test
    public void takeToken_nothingProvided_statusOK() throws Exception {
        JwtUtil jwtUtil = new JwtUtil();

        when(serviceSign.takeToken(eq("user1"), eq("user1")))
                .thenReturn(jwtUtil.generateToken(new EntityUser("user1", "user1")));

        mockMvc.perform(
                post("/signin")
                        .contentType("application/json")
        ).andExpect(status().is4xxClientError());
    }

    @Test
    public void createUser_valuesOK_statusOK() throws Exception {
        //todo check out possibility of create object and call method in same expression
        JwtUtil jwtUtil = new JwtUtil();
        String token = jwtUtil.generateToken(new EntityUser("user1", "user1"));

        when(serviceSign.createUser(eq("user1"), eq("user1")))
                .thenReturn(token);

        mockMvc.perform(
                post("/signup")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(new DtoUsernamePassword("user1", "user1")))
        ).andExpect(status().isOk()).andExpect(content().string(token));
    }

    @Test
    public void createUser_nothingProvided_statusOK() throws Exception {
        JwtUtil jwtUtil = new JwtUtil();

        when(serviceSign.createUser(eq("user1"), eq("user1")))
                .thenReturn(jwtUtil.generateToken(new EntityUser("user1", "user1")));

        mockMvc.perform(
                post("/signup")
                        .contentType("application/json")
        ).andExpect(status().is4xxClientError());
    }

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ServiceSign serviceSign;
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
