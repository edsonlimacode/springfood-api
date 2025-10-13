package com.springfood.domain.service;


import com.springfood.domain.exception.NotFoundException;
import com.springfood.domain.model.City;
import com.springfood.domain.model.Kitchen;
import com.springfood.domain.model.Restaurant;
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
    private CityService cityService;

    public void save(Restaurant restaurant) {

        Kitchen kitchen = this.kitchenService.findById(restaurant.getKitchen().getId());
        City city = this.cityService.findById(restaurant.getAddress().getCity().getId());

        this.repository.save(restaurant);
    }

    @Transactional
    public Restaurant update(Long id, Restaurant restaurant) {
        restaurant.setId(id);

        Restaurant currentRestaurant = this.findById(id);

        Kitchen kitchen = this.kitchenService.findById(restaurant.getKitchen().getId());
        City city = this.cityService.findById(restaurant.getAddress().getCity().getId());

        restaurant.setPayments(currentRestaurant.getPayments());

        return this.repository.save(restaurant);
    }

    public List<Restaurant> findAll() {
        return this.repository.findAll();
    }

    public Restaurant findById(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Restaurante de ID %d , não foi encontrado", id)));
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
