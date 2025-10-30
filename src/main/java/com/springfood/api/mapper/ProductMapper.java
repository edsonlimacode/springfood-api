package com.springfood.api.mapper;

import com.springfood.api.dto.product.ProductRequestDto;
import com.springfood.api.dto.product.ProductResponseDto;
import com.springfood.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "restaurant.id", source = "restaurantId")
    Product toModel(ProductRequestDto productRequestDto);

    ProductResponseDto toDto(Product product);

    List<ProductResponseDto> toListDto(List<Product> products);

}
