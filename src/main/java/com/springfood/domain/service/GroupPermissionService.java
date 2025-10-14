package com.springfood.domain.service;

import com.springfood.core.security.JwtSecretUtils;
import com.springfood.domain.exception.NotFoundException;
import com.springfood.domain.model.Group;
import com.springfood.domain.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class GroupPermissionService {

    @Autowired
    private GroupService groupService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtSecretUtils jwtSecretUtils;

    @Transactional
    public Set<Permission> findPermissionsByGroupId(Long id) {
        Group group = this.groupService.findById(id);
        return group.getPermissions();
    }

    @Transactional
    public void attachPermissionToGroup(Long groupId, Long permissionId) {

        Group group = this.groupService.findById(groupId);

        Permission permission = this.permissionService.findById(permissionId);

        var userLogged = this.userService.findById(jwtSecretUtils.getUserId());

        var isMaster = userLogged.getGroups().stream().anyMatch(g -> g.getName().equals("MASTER"));

        if (!isMaster && permission.getName().equals("MASTER")) {
            throw new AccessDeniedException("Apenas usuários de nivél master, podem adicionar permissão MASTER a um grupo");
        } else {
            group.getPermissions().add(permission);
        }

    }

    @Transactional
    public void detachPermissionToGroup(Long groupId, Long permissionId) {

        Group group = this.groupService.findById(groupId);

        var hasPermission = group.getPermissions().stream().anyMatch(p -> p.getId().equals(permissionId));

        if (!hasPermission) {
            throw new NotFoundException(String.format("A permição de Id %d, não tem nenhum grupo de Id %d vinculado", permissionId, groupId));
        }

        Permission permission = this.permissionService.findById(permissionId);

        group.getPermissions().remove(permission);
    }
}
