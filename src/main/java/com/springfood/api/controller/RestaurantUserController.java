package com.springfood.api.controller;


import com.springfood.api.dto.user.UserResponseDto;
import com.springfood.api.mapper.user.UserMapper;
import com.springfood.domain.model.User;
import com.springfood.domain.service.UserService;
import com.springfood.domain.service.RestaurantUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/restaurants/{id}/users")
public class RestaurantUserController {

    @Autowired
    private RestaurantUserService restaurantUserService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll(@PathVariable Long id) {

        Set<User> users = this.restaurantUserService.findUsersByRestaurantId(id);

        List<UserResponseDto> userResponseDtoList = this.userMapper.toListDto(users);

        return ResponseEntity.ok(userResponseDtoList);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void bindUserToRestaurant(@PathVariable Long id, @PathVariable Long userId) {
        this.restaurantUserService.bindUserToRestaurant(id, userId);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unbindUserToRestaurant(@PathVariable Long id, @PathVariable Long userId) {
        this.restaurantUserService.detachUserToRestaurant(id, userId);
    }
}
