package com.springfood.domain.service;

import com.springfood.core.security.CheckSecurity;
import com.springfood.domain.exception.NotFoundException;
import com.springfood.domain.model.Group;
import com.springfood.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class GroupUserService {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;


    @CheckSecurity.AdminAndMaster
    public Set<Group> groupByUserId(Long id){
        User users = this.userService.findById(id);
        return users.getGroups();
    }

    @CheckSecurity.AdminAndMaster
    @Transactional
    public void attachGroupToUser(Long userId, Long groupId) {

        User user = this.userService.findById(userId);

        Group group = this.groupService.findById(groupId);

        user.getGroups().add(group);
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
