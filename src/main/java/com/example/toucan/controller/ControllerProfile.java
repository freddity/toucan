package com.example.toucan.controller;

import com.example.toucan.model.dto.DtoPassword;
import com.example.toucan.model.dto.DtoResetPassword;
import com.example.toucan.service.ServiceProfile;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This controller contains secured endpoints that allow an user to manage their account.
 * Secured mean that user have to send correct JWT token in "Authorization" header to be permissed to these endpoints.
 */
@RestController
@RequestMapping("/")
public class ControllerProfile {

    private final ServiceProfile serviceProfile;

    /**
     * A constructor with autoinjected {@link ServiceProfile}
     * @param serviceProfile busines logic for prifile management.
     */
    public ControllerProfile(ServiceProfile serviceProfile) {
        this.serviceProfile = serviceProfile;
    }

    /**
     * This endpoint allow to
     * @param pathUsername
     * @param dtoResetPassword
     */
    @PatchMapping("/{username}/profile")
    public void resetPassword(@PathVariable("username") String pathUsername,
                              @Valid @NonNull @RequestBody DtoResetPassword dtoResetPassword) {
        serviceProfile.resetPasswordProvider(pathUsername, dtoResetPassword);
    }

    @DeleteMapping("/{username}/profile")
    public void deleteAccount(@PathVariable("username") String username,
                              @Valid @NonNull @RequestBody DtoPassword dtoPassword) {
        serviceProfile.deleteAccount(username, dtoPassword);
    }
}