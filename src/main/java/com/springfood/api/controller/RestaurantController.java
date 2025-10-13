package com.springfood.api.controller;


import com.springfood.api.dto.restaurant.RestaurantRequestDto;
import com.springfood.api.dto.restaurant.RestaurantResponseDto;
import com.springfood.api.mapper.restaurant.RestaurantMapper;
import com.springfood.core.security.CheckSecurity;
import com.springfood.domain.exception.BadRequestException;
import com.springfood.domain.exception.CityNotFoundException;
import com.springfood.domain.exception.KitchenNotFoundException;
import com.springfood.domain.model.Restaurant;
import com.springfood.domain.service.KitchenService;
import com.springfood.domain.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private KitchenService kitchenService;

    @Autowired
    private RestaurantMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody RestaurantRequestDto restaurantRequestDto) {

        try {
            Restaurant restaurant = mapper.toModel(restaurantRequestDto);

            this.restaurantService.save(restaurant);
        } catch (KitchenNotFoundException | CityNotFoundException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @CheckSecurity.Restaurants.Manager
    @PutMapping(path = "/{id}")
    public ResponseEntity<RestaurantResponseDto> update(@PathVariable Long id, @Valid @RequestBody RestaurantRequestDto restaurantRequestDto) {

        try {
            Restaurant restaurant = mapper.toModel(restaurantRequestDto);

            Restaurant restaurantUpdated = this.restaurantService.update(id, restaurant);

            RestaurantResponseDto restaurantResponseDto = mapper.toDto(restaurantUpdated);

            return ResponseEntity.ok(restaurantResponseDto);

        } catch (KitchenNotFoundException | CityNotFoundException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponseDto>> listAll() {
        List<Restaurant> restaurantList = this.restaurantService.findAll();

        List<RestaurantResponseDto> restaurantResponseDtoList = mapper.toListDto(restaurantList);

        return ResponseEntity.ok(restaurantResponseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDto> findOne(@PathVariable Long id) {
        Restaurant restaurant = this.restaurantService.findById(id);

        RestaurantResponseDto restaurantResponseDto = mapper.toDto(restaurant);
        return ResponseEntity.ok(restaurantResponseDto);
    }

    /*@Operation(
            parameters = {
                    @Parameter(name = "Nome", description = "Nome do restaurante", example = "nordestino", in = ParameterIn.QUERY)
            }
    )*/
    @GetMapping("/search")
    public ResponseEntity<List<RestaurantResponseDto>> findByNameAndDelivery(
            @Parameter(description = "Nome do restaurante", example = "nordestino") String name, BigDecimal initialDeliveryValue, BigDecimal endDeliveryValue) {
        List<Restaurant> restaurants = this.restaurantService.findByNameAndDelivery(name, initialDeliveryValue, endDeliveryValue);

        List<RestaurantResponseDto> restaurantResponseDtoList = mapper.toListDto(restaurants);

        return ResponseEntity.ok(restaurantResponseDtoList);
    }

    @GetMapping("/free-delivery")
    public ResponseEntity<List<RestaurantResponseDto>> findByDeliveryFree() {
        List<Restaurant> restaurants = this.restaurantService.findByDeliveryFree();

        List<RestaurantResponseDto> restaurantResponseDtoList = mapper.toListDto(restaurants);
        return ResponseEntity.ok(restaurantResponseDtoList);
    }

    @CheckSecurity.Restaurants.Manager
    @PutMapping("/{id}/active")
    public void active(@PathVariable Long id) {
        this.restaurantService.active(id);
    }

    @CheckSecurity.Restaurants.Manager
    @DeleteMapping("/{id}/inactive")
    public void inactive(@PathVariable Long id) {
        this.restaurantService.inactive(id);
    }

}
