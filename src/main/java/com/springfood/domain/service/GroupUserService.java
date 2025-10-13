package com.springfood.domain.service;

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


    public Set<Group> groupByUserId(Long id){
        User users = this.userService.findById(id);
        return users.getGroups();
    }

    @Transactional
    public void bindGroupToUser(Long userId, Long groupId) {

        User user = this.userService.findById(userId);

        Group group = this.groupService.findById(groupId);

        user.getGroups().add(group);
    }

    @Transactional
    public void detachGroupToUser(Long userId, Long groupId) {

        User user = this.userService.findById(userId);

        Group group = this.groupService.findById(groupId);

        Set<Group> groups = this.groupByUserId(userId);

        if (groups.isEmpty()) {
            throw new NotFoundException(String.format("O grupo de Id %d, não tem nenhum usuário de Id %d vinculado", groupId, userId));
        }

        user.getGroups().remove(group);
    }
}
