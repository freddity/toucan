package com.example.toucan.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceUser {

    public void resetPassword(String email) {
        System.out.println("### PASSWORD RESET ###");
    }
}
