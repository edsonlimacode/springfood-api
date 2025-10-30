package com.springfood.api.mapper;

import com.springfood.api.dto.group.GroupRequestDto;
import com.springfood.api.dto.group.GroupResponseDto;
import com.springfood.domain.model.Group;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    Group toModel(GroupRequestDto groupResponseDto);

    GroupResponseDto toDto(Group group);

    List<GroupResponseDto> toListDto(Collection<Group> groups);

}
