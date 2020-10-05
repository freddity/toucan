package com.example.toucan.service;

import com.example.toucan.model.dto.DtoResetPassword;
import com.example.toucan.repository.RepositoryUser;
import com.example.toucan.security.UserDetailsServiceImpl;
import com.example.toucan.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
     *
     * @param dtoResetPassword
     */
    public void resetPasswordProvider(DtoResetPassword dtoResetPassword) {
        resetPassword(dtoResetPassword.getOldPassword(),
                dtoResetPassword.getNewPassword(),
                dtoResetPassword.getNewPasswordRe());
    }

    private void resetPassword(String oldPassword, String newPassword, String newPasswordRe) {
        System.out.println("### PASSWORD RESET ###");

        //if (newPassword.equals(newPasswordRe) && oldPassword.equals())
    }
}
