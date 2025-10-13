package com.springfood.domain.listener;

import com.springfood.domain.events.CancelOrderEvent;
import com.springfood.domain.events.ConfirmOrderEvent;
import com.springfood.domain.interfaces.SendEmailService.Message;
import com.springfood.domain.model.Order;
import com.springfood.infrastructure.service.email.SmptEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class OrderNotificationListerner {

    @Autowired
    private SmptEmailService emailService;

    //@EventListener - envia o email mesmo que a transacao der algum erro
    @TransactionalEventListener //espera a transacão ser bem sucedida no banco e depois envia.
    public void orderConfirm(ConfirmOrderEvent event) {

        Order order = event.getOrder();


        Message message = Message.builder()
                .subject(order.getRestaurant().getName() + " - Pedido confirmado")
                .body("order-confirm.html")
                .variable("order", order)
                .destination("edsonlimacode@gmail.com")
                .build();

        emailService.send(message);

    }

    @TransactionalEventListener
    public void orderCancel(CancelOrderEvent event) {

        Order order = event.getOrder();

        Message message = Message.builder()
                .subject(order.getRestaurant().getName() + " - Pedido cancelado")
                .body("order-cancel.html")
                .variable("order", order)
                .destination("edsonlimacode@gmail.com")
                .build();

        emailService.send(message);

    }

}
