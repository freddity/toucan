package com.example.toucan.model.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "USERS")
public class EntityUser implements UserDetails {

    public EntityUser(){}

    public EntityUser(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Id
    @Column(name = "uuid_user", length = 16, unique = true, nullable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    @OneToMany(targetEntity=EntityNote.class, mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EntityNote> noteList = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getRole() {
        return role;
    }

    public List<EntityNote> getNoteList() {
        return noteList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
