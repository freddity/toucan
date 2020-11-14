package com.example.toucan.service.userdetails;

import com.example.toucan.model.entity.EntityUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private String username;
    private String password;
    private String authorities;
    private boolean lockStatus;

    public UserDetailsImpl() {}

    public UserDetailsImpl(String username, String password, String authorities, boolean lockStatus) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.lockStatus = lockStatus;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(authorities));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return {@code !lockStatus}, negation operator is required, because in EntityUser {@code lockStatus} is false default.
     */
    @Override
    public boolean isAccountNonLocked() {
        return !lockStatus;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /******************************************************
     * SETTERS FOR {@link org.modelmapper.ModelMapper} /***
     */                                                /***/
    public void setUsername(String username) {         /***/
        this.username = username;                      /***/
    }                                                  /***/
                                                       /***/
    public void setPassword(String password) {         /***/
        this.password = password;                      /***/
    }                                                  /***/
                                                       /***/
    public void setAuthorities(String authorities) {   /***/
        this.authorities = authorities;                /***/
    }                                                  /***/
                                                       /***/
    public void setLockStatus(boolean lockStatus) {    /***/
        this.lockStatus = lockStatus;                  /***/
    }                                                  /***/
    /******************************************************/
}
