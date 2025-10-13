package com.springfood.domain.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String description;

    @ManyToMany(mappedBy = "payments")
    private List<Restaurant> restaurants = new ArrayList<>();
}
