package com.springfood.domain.repository;

import com.springfood.domain.model.ProductImage;

public interface ProductRepositoryQueries {

    ProductImage saveImage(ProductImage productImage);

    void removeImage(ProductImage productImage);

}
