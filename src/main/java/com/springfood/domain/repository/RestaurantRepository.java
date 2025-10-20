package com.springfood.domain.repository;

import com.springfood.domain.model.Restaurant;
import com.springfood.domain.repository.generic.CustomJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends CustomJpaRepository<Restaurant, Long>,
        RestaurantRepositoryQueries,
        JpaSpecificationExecutor<Restaurant> {

    @Query("select case when count(1) > 0 then true else false end from Restaurant r join r.users u where r.id = :restaurantId and u.id = :userId")
    boolean isRestaurantByUser(Long restaurantId, Long userId);

    @Query("From Restaurant r join r.users u where u.id = :userId")
    List<Restaurant> findRestaurantsByUserId(Long userId);


}
