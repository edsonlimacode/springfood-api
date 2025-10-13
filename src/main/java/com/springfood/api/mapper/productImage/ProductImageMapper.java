package com.springfood.api.mapper.productImage;

import com.springfood.api.dto.productImage.ProductImageResponseDto;
import com.springfood.domain.model.ProductImage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductImageMapper {

    @Autowired
    private ModelMapper mapper;

    public ProductImageResponseDto toDto(ProductImage productImage) {
        return mapper.map(productImage, ProductImageResponseDto.class);
    }


}
