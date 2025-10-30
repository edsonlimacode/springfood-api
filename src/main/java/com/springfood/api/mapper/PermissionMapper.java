package com.springfood.api.mapper;

import com.springfood.api.dto.permission.PermissionRequestDto;
import com.springfood.api.dto.permission.PermissionResponseDto;
import com.springfood.domain.model.Permission;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

     Permission toModel(PermissionRequestDto permissionResponseDto);

     PermissionResponseDto toDto(Permission permission);

     List<PermissionResponseDto> toListDto(Collection<Permission> permissions);

}
