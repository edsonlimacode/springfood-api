package com.springfood.api.dto.orderItem;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemResponseDto {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "2")
    private Integer quantity;

    @Schema(example = "29.90")
    private BigDecimal totalPrice;

    @Schema(example = "14.95")
    private BigDecimal unitPrice;

    @Schema(example = "Pizza de Calabresa")
    private String productName;
}
