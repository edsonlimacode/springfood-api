package com.springfood.api.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponseDto {

    @Schema(example = "1")
    private Long id;
    @Schema(example = "Coca-Cola Diet 350ml")
    private String name;
    @Schema(example = "5.99")
    private BigDecimal price;

    @Schema(example = "true")
    private Boolean status;
}
