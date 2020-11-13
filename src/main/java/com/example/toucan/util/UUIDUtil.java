package com.example.toucan.util;

import java.util.UUID;

public class UUIDUtil {

    public static boolean isUUID(String s) {
        try {
            UUID.fromString(s);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
