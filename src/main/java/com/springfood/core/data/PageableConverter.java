package com.springfood.core.data;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PageableConverter {

    public static Pageable convertProperties(Pageable pageable, Map<String, String> properties) {

        List<Sort.Order> orders = pageable.getSort().stream()
                .filter(order -> properties.containsKey(order.getProperty()))
                .map(order ->
                        new Sort.Order(order.getDirection(), properties.get(order.getProperty()))
                ).collect(Collectors.toList());

        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(orders));
    }
}
