package com.example.toucan.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class DtoUserSignUp implements Serializable {

    @NotBlank
    private final String username;

    @NotBlank
    private final String password;

    @NotBlank
    private final String role;

    public DtoUserSignUp(@JsonProperty("username") String username,
                         @JsonProperty("password") String password,
                         @JsonProperty("role") String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
