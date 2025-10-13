package com.springfood.domain.repository;

import com.springfood.domain.model.Restaurant;
import com.springfood.domain.repository.generic.CustomJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface RestaurantRepository extends CustomJpaRepository<Restaurant, Long>,
        RestaurantRepositoryQueries,
        JpaSpecificationExecutor<Restaurant> {

    @Query("select case when count(1) > 0 then true else false end from Restaurant r join r.users u where r.id = :restaurantId and u.id = :userId")
    boolean getRestaurantByUserId(Long restaurantId, Long userId);

}
