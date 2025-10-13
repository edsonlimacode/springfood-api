package com.springfood.domain.service;

import com.springfood.domain.exception.NotFoundException;
import com.springfood.domain.model.Product;
import com.springfood.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestaurantService restaurantService;

    public void create(Product product) {

        this.restaurantService.findById(product.getRestaurant().getId());

        this.productRepository.save(product);
    }

    public Product update(Long id, Product product) {
        product.setId(id);

        this.findById(product.getId());

        this.findProductByRestaurantId(id, product.getRestaurant().getId());

        return this.productRepository.save(product);
    }

    public Product findById(Long id) {
        return this.productRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Produto de Id %d não foi encontrado", id)));
    }

    public void delete(Long id, Long restaurantId) {

        Product product = this.findById(id);

        this.findProductByRestaurantId(id, restaurantId);

        this.productRepository.deleteById(id);
    }

    public Product findProductByRestaurantId(Long id, Long restaurantId) {

        this.restaurantService.findById(restaurantId);

        return this.productRepository.findProductByRestaurantId(id, restaurantId).orElseThrow(() ->
                new NotFoundException(String.format("O produto de Id %d, não pertence ao restaurante de Id %d", id, restaurantId)));
    }

    public List<Product> findAllProductByRestaurantId(Long restaurantId) {

        this.restaurantService.findById(restaurantId);

        return this.productRepository.findAllProductsByRestaurantId(restaurantId);

    }
}
