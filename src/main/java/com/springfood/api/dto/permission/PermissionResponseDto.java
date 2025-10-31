package com.springfood.api.dto.permission;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionResponseDto {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "ADMIN")
    private String name;

    @Schema(example = "Permissão de administrador do sistema")
    private String description;
}
