package com.springfood.domain.repository;

import com.springfood.domain.model.Product;
import com.springfood.domain.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryQueries {

    @Query("select p from Product p where p.id = :id and p.restaurant.id = :restaurantId")
    Optional<Product> findProductByRestaurantId(Long id, Long restaurantId);

    @Query("select p from Product p where p.restaurant.id = :restaurantId")
    List<Product> findAllProductsByRestaurantId(Long restaurantId);


    @Query("select f from ProductImage f join f.product p where f.product.id = :productId and p.restaurant.id = :restaurantId")
    Optional<ProductImage> findByProductId(Long productId, Long restaurantId);

}
