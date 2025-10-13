package com.springfood.api.dto.orderItem;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemResponseDto {

    private Long id;

    private Integer quantity;

    private BigDecimal totalPrice;

    private BigDecimal unitPrice;

   // private String observation;

    private String productName;
}
