package com.springfood.domain.service;


import com.springfood.domain.exception.ConflictException;
import com.springfood.domain.exception.KitchenNotFoundException;
import com.springfood.domain.model.Kitchen;
import com.springfood.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KitchenService {

    @Autowired
    private KitchenRepository repository;

    public void create(Kitchen kitchen) {
        this.repository.save(kitchen);
    }

    public Kitchen update(Long id, Kitchen kitchen) {
        kitchen.setId(id);

        this.findById(kitchen.getId());

        return this.repository.save(kitchen);
    }

    public Page<Kitchen> listAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public List<Kitchen> findAllByName(String name) {
        return this.repository.findAllByName(name);
    }

    public Optional<Kitchen> findByName(String name) {
        return this.repository.findByName(name);
    }

    public Kitchen findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new KitchenNotFoundException(String.format("Cozinha de Id %d não foi encontrado",id)));
    }

    public void delete(Long id) {

        Kitchen kitchen = this.findById(id);

        if (kitchen.getRestaurants().size() > 0) {
            throw new ConflictException("Não foi possível excluir a cozinha, está em uso por outra entidade");
        }

        this.repository.deleteById(id);
    }

}
