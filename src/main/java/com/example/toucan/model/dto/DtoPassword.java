package com.example.toucan.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class DtoPassword implements Serializable {

    @NotBlank
    private final String password;

    public DtoPassword(@JsonProperty("password") @NotBlank String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
