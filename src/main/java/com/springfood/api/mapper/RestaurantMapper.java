package com.springfood.api.mapper;

import com.springfood.api.dto.restaurant.RestaurantRequestDto;
import com.springfood.api.dto.restaurant.RestaurantResponseDto;
import com.springfood.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    @Mapping(target = "kitchen.id", source = "kitchenId")
    @Mapping(target = "address.city.id", source = "address.cityId")
    Restaurant toModel(RestaurantRequestDto dto);

    @Mapping(target = "address.city", source = "address.city.name")
    @Mapping(target = "address.state", source = "address.city.state.name")
    RestaurantResponseDto toDto(Restaurant entity);

    List<RestaurantResponseDto> toListDto(List<Restaurant> entities);

}
