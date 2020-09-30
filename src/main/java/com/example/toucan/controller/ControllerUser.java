package com.example.toucan.controller;

import com.example.toucan.model.dto.DtoUsernamePassword;
import com.example.toucan.service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/toucan/auth")
public class ControllerUser {

    private final ServiceUser serviceUser;

    @Autowired
    public ControllerUser(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    @PostMapping("/signup")
    @PreAuthorize("permitAll()")
    public void signUp(@Valid @NonNull @RequestBody DtoUsernamePassword dtoUsernamePassword){
        //todo make validation and information user about errors system
        serviceUser.createUser(dtoUsernamePassword.getUsername(), dtoUsernamePassword.getPassword());
    }

    @PostMapping("/signin")
    @PreAuthorize("permitAll()")
    public void signIn(@Valid @NonNull @RequestBody DtoUsernamePassword dtoUsernamePassword) {

    }
}
