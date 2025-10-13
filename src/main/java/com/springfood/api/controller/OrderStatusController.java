package com.springfood.api.controller;

import com.springfood.domain.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders/status")
public class OrderStatusController {

    @Autowired
    private OrderStatusService orderStatusService;

    @PutMapping("/{code}/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirm(@PathVariable String code) {
        this.orderStatusService.confirm(code);
    }
    @PutMapping("/{code}/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable String code) {
        this.orderStatusService.cancel(code);
    }
    @PutMapping("/{code}/deliver")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deliver(@PathVariable String code) {
        this.orderStatusService.deliver(code);
    }

}
