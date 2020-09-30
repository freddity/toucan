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

import java.util.Collection;

@Service
public class ServiceSign {

    private final UserDetailsServiceImpl service;
    private final RepositoryUser repositoryUser;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public ServiceSign(UserDetailsServiceImpl service, RepositoryUser repositoryUser, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.service = service;
        this.repositoryUser = repositoryUser;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public EntityUser createUser(String username, String password) throws UsernameNotFoundException { //todo bad error class, make new
        if (repositoryUser.findByUsername(username) == null) {
            return repositoryUser.save(new EntityUser(username, passwordEncoder.encode(password)));
        }
        throw new UsernameNotFoundException(username);
    }

    //todo everything works but canUserBeLogin() works incorrectly
    public String generateToken(DtoUsernamePassword dto) throws NullPointerException {
        if (canUserBeLogin(dto.getUsername(), dto.getPassword())) {
            System.out.println("CAN BE LOGGED");
            return jwtUtil.generateToken(service.loadUserByUsername(dto.getUsername()));
        }
        return null;
    }

    private boolean canUserBeLogin(String username, String password) throws NullPointerException {
        if (passwordEncoder.matches(repositoryUser.findByUsername(username).getPassword(), password)) return true;
        return false;
    }
}
