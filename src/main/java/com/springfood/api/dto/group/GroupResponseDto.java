package com.springfood.api.dto.group;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupResponseDto {

    @Schema(example = "1")
    private Long id;
    @Schema(example = "Gerentes")
    private String name;
}
