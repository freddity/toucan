package com.example.toucan.controller;

import com.example.toucan.model.dto.DtoResetPassword;
import com.example.toucan.service.ServiceUser;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/toucan/user")
public class ControllerUser {

    private final ServiceUser serviceUser;

    public ControllerUser(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    @PutMapping("/resetpassword")
    @PreAuthorize("isAuthenticated()")
    public void resetPassword(@RequestHeader(name="Authorization") String token,
            @Valid @NonNull @RequestBody DtoResetPassword dtoResetPassword) {
        serviceUser.resetPasswordProvider(token, dtoResetPassword);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("isAuthenticated()")
    public void deleteAccount(@RequestHeader(name="Authorization") String token) {
        serviceUser.deleteAccount(token);
    }
}