package com.springfood.domain.repository;

import com.springfood.domain.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> { }
