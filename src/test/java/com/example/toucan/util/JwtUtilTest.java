package com.example.toucan.util;

import com.example.toucan.model.entity.EntityUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JwtUtilTest {

    @Test
    public void validateToken_allValuesOK_resultTrue() {

        //given
        JwtUtil jwtUtil = new JwtUtil();
        EntityUser entityUser = new EntityUser("test", "test");
        String token = jwtUtil.generateToken(entityUser);


        //when
        boolean result = jwtUtil.validateToken(token, entityUser);

        //then
        Assertions.assertTrue(result);
    }

    @Test
    public void generateToken_allValuesOK_correctToken() {

        //given
        JwtUtil jwtUtil = new JwtUtil();
        EntityUser entityUser = new EntityUser("test", "test");

        //when
        String token = jwtUtil.generateToken(entityUser);

        //then
        boolean result = jwtUtil.validateToken(token, entityUser);
        Assertions.assertTrue(result);
    }

    @Test
    public void extractUsername_allValuesOK_correctlyExtract() {

        //given
        JwtUtil jwtUtil = new JwtUtil();
        EntityUser entityUser = new EntityUser("test", "test");
        String token = jwtUtil.generateToken(entityUser);

        //when
        String username = jwtUtil.extractUsername(token);

        //then
        Assertions.assertEquals(username, entityUser.getUsername());
    }
}
