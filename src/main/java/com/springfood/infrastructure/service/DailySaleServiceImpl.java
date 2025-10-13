package com.springfood.infrastructure.service;

import com.springfood.api.dto.sale.DailySale;
import com.springfood.api.filter.DailySaleFilters;
import com.springfood.domain.model.Order;
import com.springfood.domain.model.OrderStatus;
import com.springfood.domain.interfaces.DailySaleService;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DailySaleServiceImpl implements DailySaleService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DailySale> getAllDailySales(DailySaleFilters filters, String timeZone) {

        var builder = entityManager.getCriteriaBuilder();
        var query = builder.createQuery(DailySale.class);
        var root = query.from(Order.class);

        ArrayList<Predicate> predicates = new ArrayList<>();

        // created_at AT TIME ZONE 'UTC'
        Expression<?> utcTime = builder.function("timezone", Object.class, builder.literal("UTC"), root.get("createdAt"));

        // resultado em America/Sao_Paulo
        Expression<?> prBRTime = builder.function("timezone", Object.class, builder.literal(timeZone), utcTime);

        var data = builder.function(
                "date",
                Date.class,
                prBRTime
        );

        if (filters.getRestaurantId() != null) {
            predicates.add(builder.equal(root.get("restaurant").get("id"), filters.getRestaurantId()));
        }

        if (filters.getStartDate() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("createdAt"), filters.getStartDate()));
        }

        if (filters.getEndDate() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("createdAt"), filters.getEndDate()));
        }

        var select = builder.construct(DailySale.class,
//                data,
                builder.count(root.get("id")),
                builder.sum(root.get("total"))
        );

        predicates.add(root.get("status").in(OrderStatus.CONFIRMED, OrderStatus.DELIVERIED));

        query.select(select);
        query.where(predicates.toArray(new Predicate[0]));
        query.groupBy(data);

        return entityManager.createQuery(query).getResultList();
    }
}
