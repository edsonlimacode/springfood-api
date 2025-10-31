package com.springfood.api.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class UserUpdateRequestDto {

    @Schema(example = "João da Silva")
    @NotBlank
    private String name;

    @Schema(example = "joao@gmail.com")
    @NotBlank
    @Email
    private String email;

}
