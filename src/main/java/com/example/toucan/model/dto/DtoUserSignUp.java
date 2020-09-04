package com.example.toucan.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class DtoUserSignUp implements Serializable {

    @NotBlank
    private final String email;

    @NotBlank
    private final String username;

    @NotBlank
    private final String password;

    public DtoUserSignUp(@JsonProperty("email") String email,
                         @JsonProperty("username") String username,
                         @JsonProperty("password") String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
