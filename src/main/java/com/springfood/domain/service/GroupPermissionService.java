package com.springfood.domain.service;

import com.springfood.domain.exception.NotFoundException;
import com.springfood.domain.model.Permission;
import com.springfood.domain.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class GroupPermissionService {

    @Autowired
    private GroupService groupService;

    @Autowired
    private PermissionService permissionService;

    @Transactional
    public Set<Permission> findPermissionsByGroupId(Long id) {
        Group group = this.groupService.findById(id);
        return group.getPermissions();
    }

    @Transactional
    public void bindPermissionToGroup(Long groupId, Long permissionId){

        Group group = this.groupService.findById(groupId);

        Permission permission = this.permissionService.findById(permissionId);

        group.getPermissions().add(permission);
    }

    @Transactional
    public void detachPermissionToGroup(Long groupId, Long permissionId){

        Group group = this.groupService.findById(groupId);

        Permission permission = this.permissionService.findById(permissionId);

        Set<Permission> permissions = this.findPermissionsByGroupId(groupId);

        if(permissions.isEmpty()){
            throw new NotFoundException(String.format("A permição de Id %d, não tem nenhum grupo de Id %d vinculado", permissionId, groupId));
        }

        group.getPermissions().remove(permission);
    }
}
