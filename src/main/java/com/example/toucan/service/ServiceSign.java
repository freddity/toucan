package com.example.toucan.service;

import com.example.toucan.model.dto.DtoUsernamePassword;
import com.example.toucan.model.entity.EntityUser;
import com.example.toucan.repository.RepositoryUser;
import com.example.toucan.security.UserDetailsServiceImpl;
import com.example.toucan.util.JwtUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Service
public class ServiceSign {

    private final UserDetailsServiceImpl detailsService;
    private final RepositoryUser repositoryUser;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public ServiceSign(UserDetailsServiceImpl detailsService, RepositoryUser repositoryUser, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.detailsService = detailsService;
        this.repositoryUser = repositoryUser;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    //todo bad error class, make new
    public EntityUser createUser(String username, String password) throws UsernameNotFoundException {

        if (repositoryUser.findByUsername(username) == null) {
            return repositoryUser.save(new EntityUser(username, password));
        }
        throw new UsernameNotFoundException(username);
    }

    public String generateToken(DtoUsernamePassword dto) throws NullPointerException {

        if (canUserBeLogged(dto.getUsername(), dto.getPassword())) {
            return jwtUtil.generateToken(detailsService.loadUserByUsername(dto.getUsername()));
        }
        //todo I've found the way to throw error to client
        throw new ResponseStatusException(FORBIDDEN, "username or password are incorrectly");
    }

    //todo make checking if user does exists and other conditions
    //TODO CHANGE USERNAME TO EMAIL EVERYWHERE
    private boolean canUserBeLogged(String username, String password) throws NullPointerException {
        return detailsService.loadUserByUsername(username) != null
                && detailsService.loadUserByUsername(username).getPassword().equals(password);
    }
}
