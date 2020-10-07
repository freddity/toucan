package com.example.toucan.service;

import com.example.toucan.model.dto.DtoResetPassword;
import com.example.toucan.repository.RepositoryUser;
import com.example.toucan.security.UserDetailsServiceImpl;
import com.example.toucan.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.toucan.util.JwtUtil.extractUsername;

@Service
public class ServiceUser {

    private final UserDetailsServiceImpl service;
    private final RepositoryUser repositoryUser;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /**
     * Constructor for auto injection by Spring Boot.
     * @param service {@link UserDetailsServiceImpl} for load user by username.
     * @param repositoryUser {@link RepositoryUser} for get data about user.
     * @param passwordEncoder {@link PasswordEncoder} for encode or compare passwords.
     * @param jwtUtil {@link JwtUtil} utilities for creating, and managing JWT Tokens.
     */
    public ServiceUser(UserDetailsServiceImpl service, RepositoryUser repositoryUser, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.service = service;
        this.repositoryUser = repositoryUser;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    /**
     * It is bridge between {@link com.example.toucan.controller.ControllerUser} and this service.
     * I've used {@code token.substring(7)} because ons start of token is {@code "Bearer "}, which must be deleted.
     * @param dto {@link DtoResetPassword} which contains old pass, new pass and repeated new password.
     */
    public void resetPasswordProvider(String token, DtoResetPassword dto) {
        resetPassword(token.substring(7), dto.getOldPassword(), dto.getNewPassword(), dto.getNewPasswordRe());
    }

    /**
     * Proper change password method.
     * @param token
     * @param oldPassword
     * @param newPassword
     * @param newPasswordRe
     */
    private void resetPassword(String token, String oldPassword, String newPassword, String newPasswordRe) {
        if (oldPassword.equals(service.loadUserByUsername(extractUsername(token)).getPassword())
                && newPassword.equals(newPasswordRe)) {

            repositoryUser.findByUsername(extractUsername(token)).setPassword(newPassword);
        }
    }
}
