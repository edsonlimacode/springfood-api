package com.springfood.domain.events;


import com.springfood.domain.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CancelOrderEvent {

    private Order order;

}
