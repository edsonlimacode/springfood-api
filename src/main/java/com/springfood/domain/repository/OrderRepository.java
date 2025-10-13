package com.springfood.domain.repository;

import com.springfood.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    @Query("from Order o join fetch o.user join fetch o.restaurant r join fetch r.kitchen")
    List<Order> findAll();

    Optional<Order> findByCode(String code);

}
