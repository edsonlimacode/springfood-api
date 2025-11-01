package com.springfood.api.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequestDto {

    @Schema(example = "Coca-Cola Diet 350ml")
    @NotBlank
    private String name;

    @Schema(example = "5.99")
    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    private Boolean status = Boolean.TRUE;

    @Schema(example = "1")
    @NotNull
    private Long restaurantId;

}
