package com.example.toucan.service;

import com.example.toucan.model.dto.DtoPassword;
import com.example.toucan.model.dto.DtoResetPassword;
import com.example.toucan.repository.RepositoryUser;
import com.example.toucan.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ServiceProfile {

    private final RepositoryUser repositoryUser;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /**
     * Constructor for auto injection by Spring Boot.
     * @param repositoryUser {@link RepositoryUser} for get data about user.
     * @param passwordEncoder {@link PasswordEncoder} for encode or compare passwords.
     * @param jwtUtil {@link JwtUtil} utilities for creating, and managing JWT Tokens.
     */
    public ServiceProfile(RepositoryUser repositoryUser, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.repositoryUser = repositoryUser;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    /**
     * It is bridge between {@link com.example.toucan.controller.ControllerProfile} and this service.
     * I've used {@code token.substring(7)} because ons start of token is {@code "Bearer "}, which must be deleted.
     * @param dto {@link DtoResetPassword} which contains old pass, new pass and repeated new password.
     */
    public void resetPasswordProvider(String pathUsername, DtoResetPassword dto) {
        resetPassword(pathUsername, dto.getOldPassword(), dto.getNewPassword(), dto.getNewPasswordRe());
    }

    /**
     * Proper change password method.
     * @param oldPassword actual password
     * @param newPassword will be set as password if equal to {@code newPasswordRe}
     * @param newPasswordRe repeated password, mus be same as {@code newPassword}
     */
    private void resetPassword(String pathUsername, String oldPassword, String newPassword, String newPasswordRe) {
        if (repositoryUser.findByUsername(pathUsername).getPassword().equals(oldPassword)) {
            if (newPassword.equals(newPasswordRe)) {
                repositoryUser.changePassword(pathUsername, newPassword);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords are not the same.");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong current password.");
        }
    }

    /**
     * This method is called when user want to delete own account.
     * Default lockStatus in {@link com.example.toucan.model.entity.EntityUser} is {@code false}
     * @param username of caller
     */
    public void deleteAccount(String username, DtoPassword dtoPassword) {
        if (repositoryUser.findByUsername(username).getPassword().equals(dtoPassword.getPassword())) {
            repositoryUser.setLockStatus(username, true);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Wrong password.");
        }
    }
}
