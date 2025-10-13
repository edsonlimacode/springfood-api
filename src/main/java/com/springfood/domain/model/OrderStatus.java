package com.springfood.domain.model;

public enum OrderStatus {
    CREATED("criado"),
    CONFIRMED("confirmado"),
    DELIVERIED("entregue"),
    CANCELED("cancelado");

    private String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
