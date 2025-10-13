package com.springfood.api.mapper.user;

import com.springfood.api.dto.user.UserCreateRequestDto;
import com.springfood.api.dto.user.UserResponseDto;
import com.springfood.api.dto.user.UserUpdateRequestDto;
import com.springfood.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper mapper;

    public User toModel(UserCreateRequestDto userCreateRequestDto) {
        return mapper.map(userCreateRequestDto, User.class);
    }

    public User toModel(UserUpdateRequestDto userUpdateRequestDto) {
        return mapper.map(userUpdateRequestDto, User.class);
    }

    public void copyPropertiesToModel(UserUpdateRequestDto userUpdateRequestDto, User user) {
         mapper.map(userUpdateRequestDto, user);
    }

    public UserResponseDto toDto(User user) {
        return mapper.map(user, UserResponseDto.class);
    }

    public List<UserResponseDto> toListDto(Collection<User> users) {
        return users.stream().map(this::toDto).collect(Collectors.toList());
    }

}
