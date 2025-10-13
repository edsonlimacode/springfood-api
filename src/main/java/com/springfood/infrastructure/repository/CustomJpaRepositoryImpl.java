package com.springfood.infrastructure.repository;

import com.springfood.domain.repository.generic.CustomJpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import jakarta.persistence.EntityManager;
import java.util.Optional;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID> {

    private EntityManager entityManager;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);

        this.entityManager = entityManager;
    }


    @Override
    public Optional<T> findFirst() {

        String jpql = "from " + getDomainClass().getName();

        T singleResult = this.entityManager.createQuery(jpql, getDomainClass()).setMaxResults(1).getSingleResult();

        return Optional.ofNullable(singleResult);
    }
}
