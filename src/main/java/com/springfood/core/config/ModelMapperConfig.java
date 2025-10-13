package com.springfood.core.config;

import com.springfood.api.dto.address.AddressResponseDto;
import com.springfood.api.dto.orderItem.OrderItemRequestDto;
import com.springfood.domain.model.Address;
import com.springfood.domain.model.OrderItem;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Address.class, AddressResponseDto.class)
                .addMapping(source -> source.getCity().getName(), AddressResponseDto::setCity)
                .addMapping(source -> source.getCity().getState().getName(), AddressResponseDto::setState);

        /*Evita o erro - Resolved [org.springframework.dao.InvalidDataAccessApiUsageException: detached entity passed to persist: com.springfood.domain.model.OrderItem; nested exception is org.hibernate.PersistentObjectException: detached entity passed to persist: com.springfood.domain.model.OrderItem]
         este erro acontece pq o modelmapper tenta converter o productId OrderItemRequestDto, para o Id em OrderItem.
        * */
        modelMapper.createTypeMap(OrderItemRequestDto.class, OrderItem.class)
                .addMappings(mapper -> mapper.skip(OrderItem::setId));

        return modelMapper;
    }

}
