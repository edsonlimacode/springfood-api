package com.springfood.api.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthchecks")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<?> healthcheck() {
        return ResponseEntity.ok("Serviço ok");
    }

}
