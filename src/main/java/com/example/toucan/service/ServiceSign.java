package com.example.toucan.service;

import com.example.toucan.model.dto.DtoUsernamePassword;
import com.example.toucan.model.entity.EntityUser;
import com.example.toucan.repository.RepositoryUser;
import com.example.toucan.util.JwtUtil;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;

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

    public EntityUser createUser(String username, String password) throws UsernameNotFoundException {

        if (repositoryUser.findByUsername(username) == null) {
            return repositoryUser.save(new EntityUser(username, password));
        }
        throw new ResponseStatusException(CONFLICT, "Username is already taken.");
    }

    public String generateToken(DtoUsernamePassword dto) {
        if (canUserBeLogged(dto.getUsername(), dto.getPassword())) {
            return jwtUtil.generateToken(repositoryUser.findByUsername(dto.getUsername()));
        } else {
            throw new ResponseStatusException(FORBIDDEN, "Username or password are incorrect or account been deleted or account doesn't exist");
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