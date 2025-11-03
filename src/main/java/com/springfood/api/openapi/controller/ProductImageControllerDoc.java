package com.springfood.api.openapi.controller;

import com.springfood.api.dto.productImage.ProductImageRequestDto;
import com.springfood.api.dto.productImage.ProductImageResponseDto;
import com.springfood.api.mapper.ProductImageMapper;
import com.springfood.core.security.CheckSecurity;
import com.springfood.domain.exception.NotFoundException;
import com.springfood.domain.interfaces.FileStorageService;
import com.springfood.domain.model.FileUpload;
import com.springfood.domain.model.Product;
import com.springfood.domain.model.ProductImage;
import com.springfood.domain.repository.ProductRepository;
import com.springfood.domain.service.ProductImageService;
import com.springfood.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes")
public interface ProductImageControllerDoc {

    @Operation(summary = "Upload da imagem do produto")
    public ProductImageResponseDto upload(@Parameter(description = "ID do restaurante", example = "1") Long restaurantId,
                                          @Parameter(description = "ID do produto", example = "1") Long productId,
                                          @RequestBody(required = true) ProductImageRequestDto productImageRequestDto) throws IOException ;

    @Operation(summary = "Busca a imagem do produto")
    public ResponseEntity<?> getImage(@Parameter(description = "ID do restaurante", example = "1") Long restaurantId,
                                      @Parameter(description = "ID do produto", example = "1") Long productId);

    @Operation(summary = "Exclui a imagem do produto")
    public void deleteImage(@Parameter(description = "ID do restaurante", example = "1") Long restaurantId,
                            @Parameter(description = "ID do produto", example = "1") Long productId);
}