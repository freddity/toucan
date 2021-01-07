package com.example.toucan.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UUIDUtilTest {

    @Test
    public void isUUID_UUIDCorrect_resultTrue() {

        //given
        String uuid = String.valueOf(UUID.randomUUID());

        //when
        boolean result = UUIDUtil.isUUID(uuid);

        //then
        Assertions.assertTrue(result);
    }
}
