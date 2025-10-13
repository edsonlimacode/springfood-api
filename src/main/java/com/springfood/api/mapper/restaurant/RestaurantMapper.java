package com.springfood.api.mapper.restaurant;

import com.springfood.api.dto.restaurant.RestaurantRequestDto;
import com.springfood.api.dto.restaurant.RestaurantResponseDto;
import com.springfood.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantMapper {

    @Autowired
    private ModelMapper mapper;

    public Restaurant toModel(RestaurantRequestDto restaurantResponseDto) {
        return mapper.map(restaurantResponseDto, Restaurant.class);
    }

    public RestaurantResponseDto toDto(Restaurant restaurant) {
        return mapper.map(restaurant, RestaurantResponseDto.class);
    }

    public List<RestaurantResponseDto> toListDto(List<Restaurant> restaurants) {
        return restaurants.stream().map(this::toDto).collect(Collectors.toList());
    }

}
