package com.springfood.domain.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "product_image")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductImage {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "product_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Product product;

    private String name;
    private String type;
    private Long size;
}
