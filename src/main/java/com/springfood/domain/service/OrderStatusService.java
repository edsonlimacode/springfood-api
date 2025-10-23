package com.springfood.domain.service;


import com.springfood.core.security.JwtSecretUtils;
import com.springfood.domain.exception.BadRequestException;
import com.springfood.domain.model.Order;
import com.springfood.domain.model.OrderStatus;
import com.springfood.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderStatusService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private JwtSecretUtils jwtSecretUtils;

    @Transactional
    public void confirm(String code) {

        Order order = this.orderService.findByCode(code);

        this.hasPermission(order);

        if (!order.getStatus().equals(OrderStatus.CREATED)) {
            throw new BadRequestException(String.format("O pedido de código %s, não pode ser alterado de %s para %s",
                    code, order.getStatus().getDescription(), OrderStatus.CONFIRMED.getDescription()));
        }

        order.confirm();
        this.orderRepository.save(order); //o event, so funciona se chamar o save(), apenas transaction nao dispara o event dentro do confirm()
    }

    @Transactional
    public void cancel(String code) {

        Order order = this.orderService.findByCode(code);

        this.hasPermission(order);

        if (!order.getStatus().equals(OrderStatus.CREATED)) {
            throw new BadRequestException(String.format("O pedido de código %s, não pode ser alterado de %s para %s",
                    code, order.getStatus().getDescription(), OrderStatus.CANCELED.getDescription()));
        }

        order.cancel();

        this.orderRepository.save(order);
    }

    @Transactional
    public void deliver(String code) {

        Order order = this.orderService.findByCode(code);

        this.hasPermission(order);

        if (!order.getStatus().equals(OrderStatus.CONFIRMED)) {
            throw new BadRequestException(String.format("O pedido de código %s, não pode ser alterado de %s para %s",
                    code, order.getStatus().getDescription(), OrderStatus.DELIVERIED.getDescription()));
        }

        order.deliver();

        this.orderRepository.save(order);
    }

    private void hasPermission(Order order) {
        var userAuthenticated = jwtSecretUtils.isUserAuthenticated(order.getUser().getId());
        var isAdminRestaurant = jwtSecretUtils.managerRestaurants(order.getRestaurant().getId());

        if (!isAdminRestaurant && !userAuthenticated) {
            throw new AccessDeniedException("Você não tem permissão para acessar este recurso");
        }
    }

}
