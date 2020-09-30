package com.example.toucan.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collection;

public class DtoUsernamePassword implements Serializable {

    @NotBlank
    private final String username;

    @NotBlank
    private final String password;

    public DtoUsernamePassword(@JsonProperty("username") String username,
                               @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
