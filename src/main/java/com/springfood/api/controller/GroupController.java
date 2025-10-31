package com.springfood.api.controller;


import com.springfood.api.dto.group.GroupRequestDto;
import com.springfood.api.dto.group.GroupResponseDto;
import com.springfood.api.mapper.GroupMapper;
import com.springfood.api.openapi.controller.GroupControllerDoc;
import com.springfood.core.security.CheckSecurity;
import com.springfood.domain.model.Group;
import com.springfood.domain.service.GroupService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController implements GroupControllerDoc {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupMapper mapper;

    @CheckSecurity.Master
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save( @Valid @RequestBody GroupRequestDto groupRequestDto) {

        Group group = this.mapper.toModel(groupRequestDto);

        this.groupService.create(group);
    }

    @CheckSecurity.Master
    @PutMapping("/{id}")
    public ResponseEntity<GroupResponseDto> update(@PathVariable Long id, @Valid @RequestBody GroupRequestDto groupRequestDto) {

        Group group = this.mapper.toModel(groupRequestDto);
        Group groupUpdated = this.groupService.update(id, group);

        GroupResponseDto groupResponseDto = this.mapper.toDto(groupUpdated);

        return ResponseEntity.ok(groupResponseDto);
    }

    @CheckSecurity.AdminAndMaster
    @GetMapping
    public ResponseEntity<List<GroupResponseDto>> list() {
        List<Group> groups = this.groupService.listAll();

        List<GroupResponseDto> groupResponseDtoList = this.mapper.toListDto(groups);

        return ResponseEntity.ok(groupResponseDtoList);
    }

    @CheckSecurity.AdminAndMaster
    @GetMapping("/{id}")
    public ResponseEntity<GroupResponseDto> findOne(@PathVariable Long id) {
        Group group = this.groupService.findById(id);

        GroupResponseDto groupResponseDto = this.mapper.toDto(group);

        return ResponseEntity.ok(groupResponseDto);
    }

    @CheckSecurity.Master
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        this.groupService.delete(id);
    }

}
