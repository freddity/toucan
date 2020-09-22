package com.example.toucan.security;

import com.example.toucan.model.dao.UserDetailsImpl;
import com.example.toucan.model.entity.EntityUser;
import com.example.toucan.repository.RepositoryUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private RepositoryUser repositoryUser;

    public UserDetailsServiceImpl(RepositoryUser repositoryUser) {
        this.repositoryUser = repositoryUser;
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        EntityUser entityUser = repositoryUser.findByUsername(username);
        if(entityUser == null) throw new UsernameNotFoundException(username);

        return new UserDetailsImpl(entityUser.getUsername(), entityUser.getPassword(), entityUser.getRole());
    }
}
