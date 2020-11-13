package com.example.toucan.service.userdetails;

import com.example.toucan.model.entity.EntityUser;
import com.example.toucan.repository.RepositoryUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final RepositoryUser repositoryUser;
    private final ModelMapper modelMapper;

    public UserDetailsServiceImpl(RepositoryUser repositoryUser, ModelMapper modelMapper) {
        this.repositoryUser = repositoryUser;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String username) {
        EntityUser entityUser = repositoryUser.findByUsername(username);
        if(entityUser == null) throw new NullPointerException();

        return modelMapper.map(entityUser, UserDetailsImpl.class);
    }

    public boolean doesUserExists(String username) {
        EntityUser entityUser = repositoryUser.findByUsername(username);
        return !Objects.isNull(entityUser);
    }
}
