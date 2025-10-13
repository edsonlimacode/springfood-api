package com.springfood.domain.service;


import com.springfood.domain.exception.NotFoundException;
import com.springfood.domain.model.Permission;
import com.springfood.domain.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository repository;

    public void create(Permission permission) {
        this.repository.save(permission);
    }

    public Permission update(Long id, Permission permission) {
        permission.setId(id);

        this.findById(permission.getId());

        return this.repository.save(permission);
    }

    public List<Permission> listAll() {
        return repository.findAll();
    }
    
    public Permission findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Permissão de Id %d não foi encontrado",id)));
    }

    public void delete(Long id) {

        this.findById(id);
        
        this.repository.deleteById(id);
    }

}
