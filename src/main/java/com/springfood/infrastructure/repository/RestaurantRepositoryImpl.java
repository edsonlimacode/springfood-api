package com.springfood.infrastructure.repository;

import com.springfood.domain.model.Restaurant;
import com.springfood.domain.repository.RestaurantRepository;
import com.springfood.domain.repository.RestaurantRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.springfood.infrastructure.especifications.RestaurantSpecs.withFreeDelivery;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    @Lazy //cria a instancia apenas quando for chamado, evitanto erro de referencia circular entre os repositorios
    private RestaurantRepository repository;

    public List<Restaurant> find(String name, BigDecimal startlDeliveryValue, BigDecimal endDeliveryValue) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Restaurant> criteriaQuery = builder.createQuery(Restaurant.class);
        Root<Restaurant> root = criteriaQuery.from(Restaurant.class);

        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(name)) {
            predicates.add(builder.like(root.get("name"), "%" + name + "%"));
        }

        if (startlDeliveryValue != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("delivery"), startlDeliveryValue));
        }

        if (endDeliveryValue != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("delivery"), endDeliveryValue));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restaurant> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<Restaurant> withDeliveryFree() {
        return this.repository.findAll(withFreeDelivery());
    }
}
