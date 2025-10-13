package com.springfood.domain.model;

import com.springfood.domain.events.CancelOrderEvent;
import com.springfood.domain.events.ConfirmOrderEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "orders")
public class Order extends AbstractAggregateRoot<Order> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String code = UUID.randomUUID().toString();

    private BigDecimal total;

    private BigDecimal subTotal;

    private BigDecimal delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;

    @Embedded
    private Address address;

    private OffsetDateTime confirmationDate;

    private OffsetDateTime deliveryDate;

    private OffsetDateTime cancelDate;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    private Payment payment;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> itens = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    public void confirm() {
        setStatus(OrderStatus.CONFIRMED);
        setConfirmationDate(OffsetDateTime.now());

        registerEvent(new ConfirmOrderEvent(this));
    }

    public void cancel() {
        setStatus(OrderStatus.CANCELED);
        setCancelDate(OffsetDateTime.now());

        registerEvent(new CancelOrderEvent(this));
    }

    public void deliver() {
        setStatus(OrderStatus.DELIVERIED);
        setDeliveryDate(OffsetDateTime.now());
    }
}
