package com.springfood.api.mapper;

import com.springfood.api.dto.user.UserCreateRequestDto;
import com.springfood.api.dto.user.UserResponseDto;
import com.springfood.api.dto.user.UserUpdateRequestDto;
import com.springfood.domain.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toModel(UserCreateRequestDto userCreateRequestDto);

    User toModel(UserUpdateRequestDto userUpdateRequestDto);

    //Mantem os valores existentes de User e altera apenas os que vierem na requisição/Dto, ex: name e email, mas não a password
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void copyUserProperties(UserUpdateRequestDto source, @MappingTarget User destination);

    UserResponseDto toDto(User user);

    List<UserResponseDto> toListDto(Collection<User> users);
}
