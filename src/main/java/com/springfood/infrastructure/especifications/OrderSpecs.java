package com.springfood.infrastructure.especifications;

import com.springfood.api.filter.OrderFilters;
import com.springfood.domain.model.Order;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;

public class OrderSpecs {

    public static Specification<Order> filters(OrderFilters filters) {

        return (root, criteriaQuery, builder) -> {

            /*como a paginação retorna um count, so vai fazer o fetch se for do tipo Order o resultado, caso contrário vai da um erro pois
            da pra fazer o fetch em um count, se nao tiver paginação esse If não é preciso
            */
            if (Order.class.equals(criteriaQuery.getResultType())) {
                //evita o N+1
                root.fetch("restaurant").fetch("kitchen");
                root.fetch("user");
            }

            ArrayList<Predicate> predicates = new ArrayList<>();

            if (filters.getUserId() != null) {
                predicates.add(builder.equal(root.get("user").get("id"), filters.getUserId()));
            }

            if (filters.getRestaurantId() != null) {
                predicates.add(builder.equal(root.get("restaurant").get("id"), filters.getRestaurantId()));
            }

            if (filters.getStartDate() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("createdAt"), filters.getStartDate()));
            }

            if (filters.getEndDate() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("createdAt"), filters.getEndDate()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
