package com.example.toucan.service;

import com.example.toucan.model.entity.EntityUser;
import com.example.toucan.repository.RepositoryUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ServiceUser {

    private final RepositoryUser repositoryUser;
    private final PasswordEncoder passwordEncoder;

    public ServiceUser(RepositoryUser repositoryUser, PasswordEncoder passwordEncoder) {
        this.repositoryUser = repositoryUser;
        this.passwordEncoder = passwordEncoder;
    }

    public EntityUser createUser(String username, String password, String role) throws UsernameAlreadyTakenException {
        if (repositoryUser.findByUsername(username) == null) {
            return repositoryUser.save(new EntityUser(username, passwordEncoder.encode(password), role));
        }
        throw new UsernameAlreadyTakenException(username);
    }


}
