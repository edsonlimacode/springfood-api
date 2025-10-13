package com.springfood.api.mapper.kitchen;

import com.springfood.api.dto.kitchen.KitchenRequestDto;
import com.springfood.api.dto.kitchen.KitchenResponseDto;
import com.springfood.domain.model.Kitchen;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class KitchenMapper {

    @Autowired
    private ModelMapper mapper;

    public Kitchen toModel(KitchenRequestDto kitchenRequestDto) {
        return mapper.map(kitchenRequestDto, Kitchen.class);
    }

    public KitchenResponseDto toDto(Kitchen kitchen) {
        return mapper.map(kitchen, KitchenResponseDto.class);
    }

    public List<KitchenResponseDto> toListDto(List<Kitchen> kitchens) {
        return kitchens.stream().map(this::toDto).collect(Collectors.toList());
    }

}
