package com.springfood.api.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class UserUpdatePasswordRequestDto {

    @NotBlank
    private String password;

    @NotBlank
    @JsonProperty("new_password")
    private String newPassword;

}
