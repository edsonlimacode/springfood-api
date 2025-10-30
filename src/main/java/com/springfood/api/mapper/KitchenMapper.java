package com.springfood.api.mapper;

import com.springfood.api.dto.kitchen.KitchenRequestDto;
import com.springfood.api.dto.kitchen.KitchenResponseDto;
import com.springfood.domain.model.Kitchen;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KitchenMapper {

    Kitchen toModel(KitchenRequestDto kitchenRequestDto);

    KitchenResponseDto toDto(Kitchen kitchen);

    List<KitchenResponseDto> toListDto(List<Kitchen> kitchens);

}
