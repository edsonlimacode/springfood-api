package com.springfood.api.mapper.permission;

import com.springfood.api.dto.permission.PermissionRequestDto;
import com.springfood.api.dto.permission.PermissionResponseDto;
import com.springfood.domain.model.Permission;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissionMapper {

    @Autowired
    private ModelMapper mapper;

    public Permission toModel(PermissionRequestDto permissionResponseDto) {
        return mapper.map(permissionResponseDto, Permission.class);
    }

    public PermissionResponseDto toDto(Permission permission) {
        return mapper.map(permission, PermissionResponseDto.class);
    }

    public List<PermissionResponseDto> toListDto(Collection<Permission> permissions) {
        return permissions.stream().map(this::toDto).collect(Collectors.toList());
    }

}
