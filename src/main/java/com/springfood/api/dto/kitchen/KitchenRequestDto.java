package com.springfood.api.dto.kitchen;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class KitchenRequestDto {

    @Schema(example = "Italian")
    @NotBlank
    @Pattern(regexp = "^[A-Z a-z]+$", message = "Nome deve ser composto apenas por letras.")
    private String name;
}
