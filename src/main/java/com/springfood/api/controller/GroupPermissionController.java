package com.springfood.api.controller;


import com.springfood.api.dto.permission.PermissionResponseDto;
import com.springfood.api.mapper.permission.PermissionMapper;
import com.springfood.core.security.CheckSecurity;
import com.springfood.domain.model.Permission;
import com.springfood.domain.service.GroupPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/groups/{id}/permissions")
public class GroupPermissionController {

    @Autowired
    private GroupPermissionService groupPermissionService;

    @Autowired
    private PermissionMapper permissionMapper;

    @CheckSecurity.AdminAndMaster
    @GetMapping
    public ResponseEntity<List<PermissionResponseDto>> findAll(@PathVariable Long id) {

        Set<Permission> permissions = this.groupPermissionService.findPermissionsByGroupId(id);

        List<PermissionResponseDto> permissionResponseDtoList = this.permissionMapper.toListDto(permissions);

        return ResponseEntity.ok(permissionResponseDtoList);
    }

    @CheckSecurity.AdminAndMaster
    @PutMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void bindPermissionToRestaurant(@PathVariable Long id, @PathVariable Long permissionId) {
        this.groupPermissionService.attachPermissionToGroup(id, permissionId);
    }

    @CheckSecurity.AdminAndMaster
    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void detachPermissionToRestaurant(@PathVariable Long id, @PathVariable Long permissionId) {
        this.groupPermissionService.detachPermissionToGroup(id, permissionId);
    }
}
