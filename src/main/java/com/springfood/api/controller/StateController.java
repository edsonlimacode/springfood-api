package com.springfood.api.controller;


import com.springfood.domain.model.State;
import com.springfood.domain.repository.StateRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("states")
@SecurityRequirement(name = "security_auth")
public class StateController {

    @Autowired
    private StateRepository repository;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<State> list() {
        return repository.findAll();
    }
}
