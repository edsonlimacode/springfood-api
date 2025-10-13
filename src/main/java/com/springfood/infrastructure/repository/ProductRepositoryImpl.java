package com.springfood.infrastructure.repository;

import com.springfood.domain.model.ProductImage;
import com.springfood.domain.repository.ProductRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public ProductImage saveImage(ProductImage productImage) {
        return entityManager.merge(productImage);
    }

    @Override
    public void removeImage(ProductImage productImage) {
        this.entityManager.remove(productImage);
    }
}
