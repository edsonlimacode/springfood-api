package com.springfood.api.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponseDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private Boolean status;
}
