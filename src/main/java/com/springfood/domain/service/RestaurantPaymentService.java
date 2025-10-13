package com.springfood.domain.service;

import com.springfood.domain.exception.NotFoundException;
import com.springfood.domain.model.Payment;
import com.springfood.domain.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class RestaurantPaymentService {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private PaymentService paymentService;

    public Set<Payment> findPaymentsByRestaurantId(Long id) {
        Restaurant restaurant = this.restaurantService.findById(id);
        return restaurant.getPayments();
    }

    @Transactional
    public void bindPaymentToRestaurant(Long restaurantId, Long paymentId) {

        Restaurant restaurant = this.restaurantService.findById(restaurantId);

        Payment payment = this.paymentService.findById(paymentId);

        restaurant.getPayments().add(payment);
    }

    @Transactional
    public void detachPaymentToRestaurant(Long restaurantId, Long paymentId) {

        Restaurant restaurant = this.restaurantService.findById(restaurantId);

        Payment payment = this.paymentService.findById(paymentId);

        Set<Payment> payments = this.findPaymentsByRestaurantId(restaurantId);

        if (payments.isEmpty()) {
            throw new NotFoundException(String.format("O pagamento de Id %d, não tem nenhum restaurante de Id %d vinculado", paymentId, restaurantId));
        }

        restaurant.getPayments().remove(payment);
    }
}
