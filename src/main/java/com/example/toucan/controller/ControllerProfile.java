package com.example.toucan.controller;

import com.example.toucan.model.dto.DtoPassword;
import com.example.toucan.model.dto.DtoResetPassword;
import com.example.toucan.service.ServiceProfile;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class ControllerProfile {

    private final ServiceProfile serviceProfile;

    public ControllerProfile(ServiceProfile serviceProfile) {
        this.serviceProfile = serviceProfile;
    }

    @PatchMapping("/{username}/profile")
    public void resetPassword(@PathVariable("username") String pathUsername,
                              @RequestHeader(name="Authorization") String token,
                              @Valid @NonNull @RequestBody DtoResetPassword dtoResetPassword) {
        serviceProfile.resetPasswordProvider(pathUsername, token, dtoResetPassword);
    }

    @DeleteMapping("/{username}/profile")
    public void deleteAccount(@PathVariable("username") String username,
                              @RequestHeader(name="Authorization") String token,
                              @Valid @NonNull @RequestBody DtoPassword dtoPassword) {
        serviceProfile.deleteAccount(token, dtoPassword);
    }

    /*@ExceptionHandler({ SignatureException.class })
    public void handleException() {
        System.out.println("SignatureException");
    }*/
}