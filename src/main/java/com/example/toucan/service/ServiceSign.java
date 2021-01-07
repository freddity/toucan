package com.example.toucan.service;

import com.example.toucan.model.dto.DtoUsernamePassword;
import com.example.toucan.model.entity.EntityUser;
import com.example.toucan.repository.RepositoryUser;
import com.example.toucan.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ServiceSign {

    private final RepositoryUser repositoryUser;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public ServiceSign(RepositoryUser repositoryUser, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.repositoryUser = repositoryUser;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String createUser(String username, String password) throws UsernameNotFoundException {

        if (repositoryUser.findByUsername(username) == null) {
            try {
                repositoryUser.save(new EntityUser(username, password));
            } catch (RuntimeException r) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while creating your account. Try again later.");
            }
            return jwtUtil.generateToken(repositoryUser.findByUsername(username));
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Username is already taken.");
    }

    public String takeToken(DtoUsernamePassword dto) {
        if (canUserBeLogged(dto.getUsername(), dto.getPassword())) {
            return jwtUtil.generateToken(repositoryUser.findByUsername(dto.getUsername()));
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Username or password are incorrect or account been deleted or account doesn't exist");
        }
    }

    private boolean canUserBeLogged(String username, String password) throws NullPointerException {
        try {
            return repositoryUser.findByUsername(username).getPassword().equals(password)
                    && !repositoryUser.findByUsername(username).isLocked();
        } catch (NullPointerException e) {
            return false;
        }
    }
}



//TODO CHANGE USERNAME TO EMAIL EVERYWHERE