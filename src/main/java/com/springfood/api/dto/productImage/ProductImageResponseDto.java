package com.springfood.api.dto.productImage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductImageResponseDto {
    private String name;
    private String type;
    private Long size;
}
