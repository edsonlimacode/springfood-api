package com.springfood.domain.service;

import com.springfood.core.security.CheckSecurity;
import com.springfood.core.security.JwtSecretUtils;
import com.springfood.domain.exception.NotFoundException;
import com.springfood.domain.model.Group;
import com.springfood.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class GroupUserService {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private JwtSecretUtils jwtSecretUtils;

    @CheckSecurity.AdminAndMaster
    public Set<Group> groupByUserId(Long id){
        User users = this.userService.findById(id);
        return users.getGroups();
    }

    @CheckSecurity.AdminAndMaster
    @Transactional
    public void attachGroupToUser(Long userId, Long groupId) {

        User user = this.userService.findById(userId);

        var userLogged = this.userService.findById(jwtSecretUtils.getUserId());

        var isMaster = userLogged.getGroups().stream().anyMatch(g -> g.getName().equals("MASTER"));

        Group group = this.groupService.findById(groupId);

        if (!isMaster && group.getName().equals("MASTER")) {
            throw new AccessDeniedException("Apenas usuários de nivél master, podem adicionar grupo MASTER a um usuário");
        } else {
            user.getGroups().add(group);
        }
    }

    @CheckSecurity.AdminAndMaster
    @Transactional
    public void detachGroupToUser(Long userId, Long groupId) {

        User user = this.userService.findById(userId);

        var hasGroup = user.getGroups().stream().anyMatch(g -> g.getId().equals(groupId));

        if(!hasGroup){
            throw new NotFoundException(String.format("O grupo de Id %d, não tem nenhum usuário de Id %d vinculado", groupId, userId));
        }

        Group group = this.groupService.findById(groupId);

        user.getGroups().remove(group);
    }
}
