package com.springfood.api.mapper.product;

import com.springfood.api.dto.product.ProductRequestDto;
import com.springfood.api.dto.product.ProductResponseDto;
import com.springfood.domain.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    @Autowired
    private ModelMapper mapper;

    public Product toModel(ProductRequestDto productRequestDto) {
        return mapper.map(productRequestDto, Product.class);
    }

    public ProductResponseDto toDto(Product product) {
        return mapper.map(product, ProductResponseDto.class);
    }

    public List<ProductResponseDto> toListDto(List<Product> products) {
        return products.stream().map(this::toDto).collect(Collectors.toList());
    }

}
