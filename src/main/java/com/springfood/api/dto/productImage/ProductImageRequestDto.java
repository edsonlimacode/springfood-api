package com.springfood.api.dto.productImage;


import com.springfood.core.validation.FileContentType;
import com.springfood.core.validation.FileSize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class ProductImageRequestDto {

//    @NotNull
    @FileSize(max = "500KB")
    @FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    private MultipartFile file;

    @NotBlank
    private String description;

}
