package com.springfood.domain.service;

import com.springfood.domain.exception.NotFoundException;
import com.springfood.domain.model.User;
import com.springfood.domain.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class RestaurantUserService {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    public Set<User> findUsersByRestaurantId(Long id) {
        Restaurant restaurant = this.restaurantService.findById(id);
        return restaurant.getUsers();
    }

    @Transactional
    public void attachUserToRestaurant(Long restaurantId, Long userId) {

        Restaurant restaurant = this.restaurantService.findById(restaurantId);

        User user = this.userService.findById(userId);

        restaurant.getUsers().add(user);
    }

    @Transactional
    public void detachUserToRestaurant(Long restaurantId, Long userId) {

        Restaurant restaurant = this.restaurantService.findById(restaurantId);

        User user = this.userService.findById(userId);

        Set<User> users = this.findUsersByRestaurantId(restaurantId);

        if (users.isEmpty()) {
            throw new NotFoundException(String.format("O usuário de Id %d, não tem nenhum restaurante de Id %d vinculado", userId, restaurantId));
        }

        restaurant.getUsers().remove(user);
    }
}
