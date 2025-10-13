package com.springfood.domain.service;

import com.springfood.domain.exception.NotFoundException;
import com.springfood.domain.model.FileUpload;
import com.springfood.domain.model.ProductImage;
import com.springfood.domain.repository.ProductRepository;
import com.springfood.domain.interfaces.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;

@Service
public class ProductImageService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Transactional
    public ProductImage saveImage(ProductImage productImage, InputStream inputStream) {

        Long productId = productImage.getProduct().getId();
        Long restaurantId = productImage.getProduct().getRestaurant().getId();

        Optional<ProductImage> image = this.productRepository.findByProductId(productId, restaurantId);

        String oldFileName = null;

        if (image.isPresent()) {
            oldFileName = image.get().getName();
            this.productRepository.removeImage(image.get());
        }

        String newFileName = fileStorageService.generateFileName(productImage.getName());

        productImage.setName(newFileName);
        productImage = this.productRepository.saveImage(productImage);
        this.productRepository.flush();

        FileUpload fileUpload = new FileUpload();
        fileUpload.setFileName(productImage.getName());
        fileUpload.setContentType(productImage.getType());
        fileUpload.setFile(inputStream);

        this.fileStorageService.upload(fileUpload);

        if (oldFileName != null) {
            this.fileStorageService.remove(oldFileName);
        }

        return productImage;
    }

    public ProductImage findImageByProductId(Long productId, Long restaurantId) {
        return this.productRepository.findByProductId(productId, restaurantId).orElseThrow(() ->
                new NotFoundException(String.format("Imagem do produdo %d, não foi encontrada", productId)));
    }

    @Transactional
    public void delete(Long productId, Long restaurantId) {
        ProductImage image = this.findImageByProductId(productId, restaurantId);

        this.productRepository.removeImage(image);
        this.fileStorageService.remove(image.getName());
    }


}
