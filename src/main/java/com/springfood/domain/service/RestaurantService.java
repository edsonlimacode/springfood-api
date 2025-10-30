package com.springfood.domain.service;


import com.springfood.core.security.JwtSecretUtils;
import com.springfood.domain.exception.NotFoundException;
import com.springfood.domain.model.Restaurant;
import com.springfood.domain.model.User;
import com.springfood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private KitchenService kitchenService;

    @Autowired
    private UserService userService;

    @Autowired
    private CityService cityService;

    @Autowired
    private JwtSecretUtils jwtSecretUtils;

    @Transactional
    public void save(Restaurant restaurant) {

        this.kitchenService.findById(restaurant.getKitchen().getId());
        this.cityService.findById(restaurant.getAddress().getCity().getId());

        Restaurant restaurantSaved = this.repository.save(restaurant);

        User user = this.userService.findById(jwtSecretUtils.getUserId());

        var admin = user.getGroups().stream().anyMatch(g -> g.getName().equals("ADMIN"));

        if (admin) {
            restaurantSaved.getUsers().add(user);
        }
    }

    @Transactional
    public Restaurant update(Long id, Restaurant restaurant) {
        restaurant.setId(id);

        Restaurant currentRestaurant = this.findById(id);

        this.kitchenService.findById(restaurant.getKitchen().getId());
        this.cityService.findById(restaurant.getAddress().getCity().getId());

        restaurant.setPayments(currentRestaurant.getPayments());
        restaurant.setUsers(currentRestaurant.getUsers());

        return this.repository.save(restaurant);
    }

    public List<Restaurant> findAll() {

        return this.repository.findRestaurantsByUserId(jwtSecretUtils.getUserId());

    }

    public Restaurant findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Restaurante de ID %d , não foi encontrado", id)));
    }

    public boolean checkRestaurantIfBelongsToUserLogged(Long restaurantId){
        Restaurant restaurant = this.findById(restaurantId);

        Optional<User> first = restaurant.getUsers().stream()
                .filter(e -> e.getId().equals(jwtSecretUtils.getUserId()))
                .findFirst();

        if (first.isPresent()) {
            return true;
        }

        return false;
    }

    public List<Restaurant> findByNameAndDelivery(String name, BigDecimal initialValue, BigDecimal endValue) {
        return this.repository.find(name, initialValue, endValue);
    }

    public List<Restaurant> findByDeliveryFree() {
        return this.repository.withDeliveryFree();
    }

    public Optional<Restaurant> findFirst() {
        return this.repository.findFirst();
    }

    @Transactional
    public void active(Long restaurantId) {

        Restaurant restaurant = this.findById(restaurantId);

        restaurant.setStatus(true);

    }

    @Transactional
    public void inactive(Long restaurantId) {

        Restaurant restaurant = this.findById(restaurantId);

        restaurant.setStatus(false);

    }
}
