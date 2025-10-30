package com.springfood.infrastructure.especifications;

import com.springfood.domain.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.math.BigDecimal;

public class RestaurantSpecs {

    public static Specification<Restaurant> withFreeDelivery() {
        return RestaurantSpecs::withFreeDeliveryPredicate;
    }

    private static Predicate withFreeDeliveryPredicate(Root<Restaurant> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        return builder.equal(root.get("delivery"), BigDecimal.ZERO);
    }
}
