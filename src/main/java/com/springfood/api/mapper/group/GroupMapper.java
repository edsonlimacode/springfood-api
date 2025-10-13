package com.springfood.api.mapper.group;

import com.springfood.api.dto.group.GroupRequestDto;
import com.springfood.api.dto.group.GroupResponseDto;
import com.springfood.domain.model.Group;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapper {

    @Autowired
    private ModelMapper mapper;


    public Group toModel(GroupRequestDto groupResponseDto) {
        return mapper.map(groupResponseDto, Group.class);
    }

    public GroupResponseDto toDto(Group group) {
        return mapper.map(group, GroupResponseDto.class);
    }

    public List<GroupResponseDto> toListDto(Collection<Group> groups) {
        return groups.stream().map(this::toDto).collect(Collectors.toList());
    }

}
