package com.example.toucan.controller;

import com.example.toucan.model.dto.DtoUsernamePassword;
import com.example.toucan.service.ServiceSign;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class ControllerSign {

    private final ServiceSign serviceSign;

    public ControllerSign(ServiceSign serviceSign) {
        this.serviceSign = serviceSign;
    }

    @PostMapping("/signup")
    public void signUp(@Valid @NonNull @RequestBody DtoUsernamePassword dtoUsernamePassword){

        //todo make validation and information user about errors system

        serviceSign.createUser(dtoUsernamePassword.getUsername(), dtoUsernamePassword.getPassword());
    }

    @PostMapping("/signin")
    public String signIn(@Valid @NonNull @RequestBody DtoUsernamePassword dtoUsernamePassword) {
        return serviceSign.generateToken(dtoUsernamePassword);
    }
}
