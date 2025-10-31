package com.springfood.api.dto.kitchen;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KitchenResponseDto {

    @Schema(example = "1")
    private Long id;
    @Schema(example = "Brazilian")
    private String name;

}
