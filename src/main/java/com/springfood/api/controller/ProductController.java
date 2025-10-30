package com.springfood.api.controller;


import com.springfood.api.dto.product.ProductRequestDto;
import com.springfood.api.dto.product.ProductResponseDto;
import com.springfood.api.mapper.ProductMapper;
import com.springfood.core.security.CheckSecurity;
import com.springfood.domain.model.Product;
import com.springfood.domain.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@SecurityRequirement(name = "security_auth")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper mapper;

    @CheckSecurity.Admin
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@Valid @RequestBody ProductRequestDto productRequestDto) {

        Product product = this.mapper.toModel(productRequestDto);

        this.productService.create(product);
    }

    @CheckSecurity.Admin
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable Long id, @Valid @RequestBody ProductRequestDto productRequestDto) {

        Product product = this.mapper.toModel(productRequestDto);

        Product productUpdated = this.productService.update(id, product);

        ProductResponseDto productResponseDto = this.mapper.toDto(productUpdated);

        return ResponseEntity.ok(productResponseDto);
    }

    @GetMapping("/{id}/restaurant/{restaurantId}")
    public ResponseEntity<ProductResponseDto> findByRestaurantId(@PathVariable Long id, @PathVariable Long restaurantId) {
        Product product = this.productService.findProductByRestaurantId(id, restaurantId);

        ProductResponseDto productResponseDto = this.mapper.toDto(product);

        return ResponseEntity.ok(productResponseDto);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<ProductResponseDto>> findAllByRestaurantId(@PathVariable Long id) {
        List<Product> allProductByRestaurantId = this.productService.findAllProductByRestaurantId(id);

        List<ProductResponseDto> productResponseDtos = this.mapper.toListDto(allProductByRestaurantId);

        return ResponseEntity.ok(productResponseDtos);

    }

    @CheckSecurity.Admin
    @DeleteMapping("/{id}/restaurant/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id, @PathVariable Long restaurantId) {
        this.productService.delete(id, restaurantId);
    }

}
