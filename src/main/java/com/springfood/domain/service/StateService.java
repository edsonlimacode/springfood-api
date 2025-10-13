package com.springfood.domain.service;

import com.springfood.domain.exception.NotFoundException;
import com.springfood.domain.model.State;
import com.springfood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public State findById(Long id) {
        return this.stateRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Estado de Id %d não foi encontrada", id)));
    }

    public List<State> findAll() {
        return this.stateRepository.findAll();
    }
}
