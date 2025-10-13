package com.springfood.domain.service;

import com.springfood.domain.exception.CityNotFoundException;
import com.springfood.domain.model.City;
import com.springfood.domain.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public City findById(Long id) {
        return this.cityRepository.findById(id).orElseThrow(
                () -> new CityNotFoundException(String.format("Cidade de Id %d não foi encontrada", id)));
    }

    public List<City> findAll() {
        return this.cityRepository.findAll();
    }
}
