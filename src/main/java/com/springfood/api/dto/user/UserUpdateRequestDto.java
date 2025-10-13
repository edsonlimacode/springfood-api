package com.springfood.api.dto.user;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class UserUpdateRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

}
