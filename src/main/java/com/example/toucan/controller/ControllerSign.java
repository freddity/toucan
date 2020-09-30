package com.example.toucan.controller;

import com.example.toucan.model.dto.DtoUsernamePassword;
import com.example.toucan.service.ServiceSign;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/toucan/auth")
public class ControllerSign {

    private final ServiceSign serviceSign;

    @Autowired
    public ControllerSign(ServiceSign serviceSign) {
        this.serviceSign = serviceSign;
    }

    @PostMapping("/signup")
    @PreAuthorize("permitAll()")
    public void signUp(@Valid @NonNull @RequestBody DtoUsernamePassword dtoUsernamePassword){
        //todo make validation and information user about errors system
        serviceSign.createUser(dtoUsernamePassword.getUsername(), dtoUsernamePassword.getPassword());

        System.out.println("SIGNUP");
    }

    @GetMapping("/signin")
    @PreAuthorize("permitAll()")
    public String signIn(@Valid @NonNull @RequestBody DtoUsernamePassword dtoUsernamePassword) {
        //todo make validation and information user about errors system

        System.out.println("SIGNIN");

        try {
            return serviceSign.generateToken(dtoUsernamePassword);
        } catch (NullPointerException e) {
            throw new NullPointerException(e.getMessage());
        }
    }
}
