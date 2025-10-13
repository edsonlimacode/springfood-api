package com.springfood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    private BigDecimal delivery;

    @ManyToOne
    private Kitchen kitchen;

    @OneToMany(mappedBy = "restaurant")
    private List<Product> products;


    @Embedded
    private Address address;

    private Boolean status = Boolean.TRUE;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "datetime")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "datetime")
    private OffsetDateTime updatedAt;

    @ManyToMany
    @JoinTable(
            name = "restaurant_payment",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_id")
    )
    private Set<Payment> payments = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "restaurant_user",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();

}
