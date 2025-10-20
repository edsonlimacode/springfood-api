package com.springfood.domain.service;


import com.springfood.domain.exception.NotFoundException;
import com.springfood.domain.model.Group;
import com.springfood.domain.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository repository;

    public void create(Group group) {
        this.repository.save(group);
    }

    public Group update(Long id, Group group) {
        group.setId(id);

        Group currentGroup = this.findById(group.getId());

        group.setPermissions(currentGroup.getPermissions());

        return this.repository.save(group);
    }

    public List<Group> listAll() {
        return repository.findAll();
    }
    
    public Group findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Grupo de Id %d não foi encontrado",id)));
    }

    public void delete(Long id) {

        this.findById(id);
        
        this.repository.deleteById(id);
    }

}
