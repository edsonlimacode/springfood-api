package com.springfood.domain.service;


import com.springfood.core.security.JwtSecretUtils;
import com.springfood.domain.exception.BadRequestException;
import com.springfood.domain.exception.NotFoundException;
import com.springfood.domain.model.*;
import com.springfood.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ProductService productService;

    @Autowired
    private JwtSecretUtils jwtSecretUtils;

    public void create(Order order) {

        Restaurant restaurant = this.restaurantService.findById(order.getRestaurant().getId());
        Payment payment = this.paymentService.findById(order.getPayment().getId());

        if (!restaurant.getPayments().contains(payment)) {
            throw new BadRequestException(String.format("A forma de pagamento de Id %d, não é aceita pelo restaurante de Id %d", payment.getId(), restaurant.getId()));
        }

        User user = new User();
        user.setId(jwtSecretUtils.getUserId());
        order.setUser(user);

        BigDecimal subTotal = order.getItens().stream().map(orderItem -> {
            Product product = this.productService.findById(orderItem.getProduct().getId());

            this.productService.findProductByRestaurantId(product.getId(), restaurant.getId());

            BigDecimal sumSubTotal = product.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()));

            orderItem.setTotalPrice(sumSubTotal);
            orderItem.setUnitPrice(product.getPrice());
            orderItem.setOrder(order);

            return sumSubTotal;

        }).reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setSubTotal(subTotal);
        order.setDelivery(restaurant.getDelivery());
        order.setTotal(subTotal.add(restaurant.getDelivery()));

        this.orderRepository.save(order);
    }

    public Page<Order> findAll(Specification<Order> filters, Pageable pageable) {
        return this.orderRepository.findAll(filters, pageable);
    }

    public Order findByCode(String code) {
        return this.orderRepository.findByCode(code).orElseThrow(() -> new NotFoundException(String.format("Pedido de Id %d não existe", code)));
    }
}
