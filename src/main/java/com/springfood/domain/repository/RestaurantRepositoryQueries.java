package com.springfood.domain.repository;

import com.springfood.domain.model.Restaurant;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepositoryQueries {

     List<Restaurant> find(String name, BigDecimal initialDeliveryValue, BigDecimal endDeliveryValue);

     List<Restaurant> withDeliveryFree();
}
