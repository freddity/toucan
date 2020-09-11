package com.example.toucan.service;

import com.example.toucan.model.entity.EntityUser;
import com.example.toucan.repository.RepositoryUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceUser {

    private final RepositoryUser repositoryUser;
    private final PasswordEncoder passwordEncoder;

    public ServiceUser(RepositoryUser repositoryUser, PasswordEncoder passwordEncoder) {
        this.repositoryUser = repositoryUser;
        this.passwordEncoder = passwordEncoder;
    }

    public EntityUser createUser(String username, String password, String role){
        return repositoryUser.save(new EntityUser(username, passwordEncoder.encode(password), role));
    }

    public List getAllNotes(String username){
        return repositoryUser
                .findByUsername(username).getNoteList();
    }

    /*public boolean isNonLocked(String username, boolean status) {
        return repositoryUser
                .findByUsername(username).isNonLocked(status);
    }*/
}
