package com.springfood.api.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class UserCreateRequestDto {

    @Schema(example = "joão")
    @NotBlank
    private String name;

    @Schema(example = "joão@gmail.com")
    @NotBlank
    @Email
    private String email;

    @Schema(example = "123456")
    @NotBlank
    private String password;

    @Schema(example = "false")
    @NotNull
    private Boolean admin;

}
