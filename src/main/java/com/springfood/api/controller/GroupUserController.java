package com.springfood.api.controller;

import com.springfood.api.dto.group.GroupResponseDto;
import com.springfood.api.mapper.group.GroupMapper;
import com.springfood.core.security.CheckSecurity;
import com.springfood.domain.model.Group;
import com.springfood.domain.service.GroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users/{id}/groups")
public class GroupUserController {

    @Autowired
    private GroupUserService groupUserService;

    @Autowired
    private GroupMapper groupMapper;

    @CheckSecurity.AdminAndMaster
    @GetMapping
    public ResponseEntity<List<GroupResponseDto>> groupsByUserId(@PathVariable Long id) {
        Set<Group> groups = this.groupUserService.groupByUserId(id);

        List<GroupResponseDto> groupResponseDtoList = this.groupMapper.toListDto(groups);

        return ResponseEntity.ok(groupResponseDtoList);
    }

    @CheckSecurity.AdminAndMaster
    @PutMapping("/{groupId}")
    public void attachGroupToUser(@PathVariable Long id, @PathVariable Long groupId) {
        this.groupUserService.attachGroupToUser(id, groupId);
    }

    @CheckSecurity.AdminAndMaster
    @DeleteMapping("/{groupId}")
    public void detachGroupToUser(@PathVariable Long id, @PathVariable Long groupId) {
        this.groupUserService.detachGroupToUser(id, groupId);
    }
}
