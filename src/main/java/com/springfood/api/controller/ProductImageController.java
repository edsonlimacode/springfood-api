package com.springfood.api.controller;

import com.springfood.api.dto.productImage.ProductImageRequestDto;
import com.springfood.api.dto.productImage.ProductImageResponseDto;
import com.springfood.api.mapper.ProductImageMapper;
import com.springfood.core.security.CheckSecurity;
import com.springfood.domain.exception.NotFoundException;
import com.springfood.domain.model.FileUpload;
import com.springfood.domain.model.Product;
import com.springfood.domain.model.ProductImage;
import com.springfood.domain.repository.ProductRepository;
import com.springfood.domain.service.ProductImageService;
import com.springfood.domain.service.ProductService;
import com.springfood.domain.interfaces.FileStorageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/restaurant/{restaurantId}/product/{productId}/image")
@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes")
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ProductImageMapper productImageMapper;

    @CheckSecurity.Admin
    @PostMapping("/upload")
    public ProductImageResponseDto upload(@PathVariable("restaurantId") Long restaurantId,
                                          @PathVariable("productId") Long productId,
                                          @Valid ProductImageRequestDto productImageRequestDto) throws IOException {

        Product product = this.productService.findProductByRestaurantId(productId, restaurantId);

        MultipartFile file = productImageRequestDto.getFile();

        ProductImage productImage = new ProductImage();
        productImage.setProduct(product);
        productImage.setName(file.getOriginalFilename());
        productImage.setType(file.getContentType());
        productImage.setSize(file.getSize());

        this.productImageService.saveImage(productImage, file.getInputStream());

        return this.productImageMapper.toDto(productImage);

    }

    @GetMapping
    public ResponseEntity<?> getImage(@PathVariable("restaurantId") Long restaurantId,
                                                        @PathVariable("productId") Long productId) {
        try {

            ProductImage image = this.productImageService.findImageByProductId(productId, restaurantId);

            FileUpload inputStream = this.fileStorageService.getInputStream(image.getName());

            if(inputStream.getUrl() != null){
                return ResponseEntity.status(HttpStatus.FOUND)
                        .header(HttpHeaders.LOCATION, inputStream.getUrl())
                        .build();
            }else {
                return ResponseEntity.ok()
                        .contentType(MediaType.valueOf(image.getType()))
                        .body(new InputStreamResource(inputStream.getFile()));
            }
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @CheckSecurity.Admin
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteImage(@PathVariable("restaurantId") Long restaurantId,
                            @PathVariable("productId") Long productId) {
        try {
            this.productImageService.delete(productId, restaurantId);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }
}