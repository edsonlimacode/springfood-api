package com.springfood.api.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "João da Silva")
    private String name;

    @Schema(example = "joao@gmail.com")
    private String email;

}
