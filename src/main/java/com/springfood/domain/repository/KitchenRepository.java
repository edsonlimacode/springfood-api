package com.springfood.domain.repository;

import com.springfood.domain.model.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KitchenRepository extends JpaRepository<Kitchen, Long> {

    List<Kitchen> findAllByName(String name);

    Optional<Kitchen> findByName(String name);

}
