package com.springfood.domain.service;

import com.springfood.core.security.JwtSecretUtils;
import com.springfood.domain.exception.NotFoundException;
import com.springfood.domain.model.Product;
import com.springfood.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private JwtSecretUtils jwtSecretUtils;

    public void create(Product product) {

        var isRestaurantBelongsToUser = this.restaurantService.checkRestaurantIfBelongsToUserLogged(product.getRestaurant().getId());

        if (!isRestaurantBelongsToUser) {
            throw new AccessDeniedException("Você não tem permissão para executar esta ação");
        }

        this.productRepository.save(product);
    }

    public Product update(Long id, Product product) {
        product.setId(id);

        this.findById(product.getId());

        this.findProductByRestaurantId(id, product.getRestaurant().getId());

        var isRestaurantBelongsToUser = this.restaurantService.checkRestaurantIfBelongsToUserLogged(product.getRestaurant().getId());

        if (!isRestaurantBelongsToUser) {
            throw new AccessDeniedException("Você não tem permissão para executar esta ação");
        }

        return this.productRepository.save(product);
    }

    public Product findById(Long id) {
        return this.productRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Produto de Id %d não foi encontrado", id)));
    }

    public void delete(Long id, Long restaurantId) {

        this.findById(id);

        Product product = this.findProductByRestaurantId(id, restaurantId);

        var isRestaurantBelongsToUser = this.restaurantService.checkRestaurantIfBelongsToUserLogged(product.getRestaurant().getId());

        if (!isRestaurantBelongsToUser) {
            throw new AccessDeniedException("Você não tem permissão para executar esta ação");
        }

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
