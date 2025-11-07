package com.springfood.api.dto.permission;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class PermissionRequestDto {

    @Schema(example = "ADMIN_EXEMPLO", description = "Nome da permissão")
    @NotBlank
    private String name;

    @Schema(example = "Permissão de exemplo para administradores", description = "Descrição da permissão")
    private String description;
}
