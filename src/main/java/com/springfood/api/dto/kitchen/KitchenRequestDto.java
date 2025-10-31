package com.springfood.api.dto.kitchen;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class KitchenRequestDto {

    @Schema(example = "Italian")
    @NotBlank
    private String name;
}
