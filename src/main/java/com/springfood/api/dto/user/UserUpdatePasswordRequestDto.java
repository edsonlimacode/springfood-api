package com.springfood.api.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class UserUpdatePasswordRequestDto {

    @Schema(example = "senhaAtual123")
    @NotBlank
    private String password;

    @Schema(example = "novaSenha456")
    @NotBlank
    @JsonProperty("new_password")
    private String newPassword;

}
