package com.springfood.api.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springfood.api.dto.restaurant.RestaurantResumeResponseDto;
import com.springfood.api.dto.user.UserResponseDto;
import com.springfood.domain.model.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class OrderResumeResponseDto {

    @Schema(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
    private String code;

    @Schema(example = "45.50")
    private BigDecimal total;

    @Schema(example = "50.00")
    @JsonProperty("sub_total")
    private BigDecimal subTotal;

    @Schema(example = "4.50")
    private BigDecimal delivery;

    @Schema(example = "CONFIRMED")
    private OrderStatus status;

    @Schema(example = "2024-05-20T15:30:00Z")
    @JsonProperty("created_at")
    private OffsetDateTime createdAt;

    private RestaurantResumeResponseDto restaurant;

    private UserResponseDto user;
}
