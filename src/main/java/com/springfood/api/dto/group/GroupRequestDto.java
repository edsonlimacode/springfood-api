package com.springfood.api.dto.group;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class GroupRequestDto {

    @Schema(example = "ADMIN")
    @NotBlank
    private String name;

}
