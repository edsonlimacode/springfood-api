package com.springfood.api.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @NotBlank
    @Email
    @Schema(example = "joão@email.com")
    private String email;

    @NotBlank
    @Schema(example = "123456")
    private String password;

}
