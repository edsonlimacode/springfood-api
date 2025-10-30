package com.springfood.api.mapper;

import com.springfood.api.dto.productImage.ProductImageResponseDto;
import com.springfood.domain.model.ProductImage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {

     ProductImageResponseDto toDto(ProductImage productImage);
}
