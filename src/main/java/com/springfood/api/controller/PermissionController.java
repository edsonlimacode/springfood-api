package com.springfood.api.controller;


import com.springfood.api.dto.permission.PermissionRequestDto;
import com.springfood.api.dto.permission.PermissionResponseDto;
import com.springfood.api.mapper.permission.PermissionMapper;
import com.springfood.domain.model.Permission;
import com.springfood.domain.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@Valid @RequestBody PermissionRequestDto permissionRequestDto) {

        Permission permission = this.mapper.toModel(permissionRequestDto);

        this.permissionService.create(permission);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionResponseDto> update(@PathVariable Long id, @Valid @RequestBody PermissionRequestDto permissionRequestDto) {

        Permission permission = this.mapper.toModel(permissionRequestDto);

        Permission permissionUpdated = this.permissionService.update(id, permission);

        PermissionResponseDto permissionResponseDto = this.mapper.toDto(permissionUpdated);

        return ResponseEntity.ok(permissionResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<PermissionResponseDto>> list() {
        List<Permission> permissions = this.permissionService.listAll();

        List<PermissionResponseDto> permissionResponseDtoList = this.mapper.toListDto(permissions);

        return ResponseEntity.ok(permissionResponseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionResponseDto> findOne(@PathVariable Long id) {
        Permission permission = this.permissionService.findById(id);

        PermissionResponseDto permissionResponseDto = this.mapper.toDto(permission);

        return ResponseEntity.ok(permissionResponseDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        this.permissionService.delete(id);
    }

}
