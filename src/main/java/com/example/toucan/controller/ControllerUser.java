package com.example.toucan.controller;

import com.example.toucan.model.dto.DtoPassword;
import com.example.toucan.model.dto.DtoResetPassword;
import com.example.toucan.service.ServiceUser;
import io.jsonwebtoken.SignatureException;
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

    @PatchMapping
    @PreAuthorize("isAuthenticated()")
    public void resetPassword(@RequestHeader(name="Authorization") String token,
            @Valid @NonNull @RequestBody DtoResetPassword dtoResetPassword) {
        serviceUser.resetPasswordProvider(token, dtoResetPassword);
    }

    @DeleteMapping
    @PreAuthorize("isAuthenticated()")
    public void deleteAccount(@RequestHeader(name="Authorization") String token, @RequestBody DtoPassword dtoPassword) {
        serviceUser.deleteAccount(token, dtoPassword);
    }

    /*@ExceptionHandler({ SignatureException.class })
    public void handleException() {
        System.out.println("SignatureException");
    }*/
}