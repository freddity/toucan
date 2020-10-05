package com.example.toucan.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class DtoResetPassword implements Serializable {

    @NotBlank
    private final String oldPassword;

    @NotBlank
    private final String newPassword;

    @NotBlank
    private final String newPasswordRe;

    public DtoResetPassword(@JsonProperty("old_password") @NotBlank String oldPassword,
                            @JsonProperty("new_password") @NotBlank String newPassword,
                            @JsonProperty("new_password_re") @NotBlank String newPasswordRe) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.newPasswordRe = newPasswordRe;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getNewPasswordRe() {
        return newPasswordRe;
    }
}
