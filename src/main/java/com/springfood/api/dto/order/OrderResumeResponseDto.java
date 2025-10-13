package com.springfood.api.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springfood.api.dto.restaurant.RestaurantResumeResponseDto;
import com.springfood.api.dto.user.UserResponseDto;
import com.springfood.domain.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class OrderResumeResponseDto {

    private String code;

    private BigDecimal total;

    @JsonProperty("sub_total")
    private BigDecimal subTotal;

    private BigDecimal delivery;

    private OrderStatus status;

    @JsonProperty("created_at")
    private OffsetDateTime createdAt;

    private RestaurantResumeResponseDto restaurant;

    private UserResponseDto user;
}
