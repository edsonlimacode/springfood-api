package com.springfood.domain.service;


import com.springfood.core.security.JwtSecretUtils;
import com.springfood.domain.exception.BadRequestException;
import com.springfood.domain.exception.NotFoundException;
import com.springfood.domain.model.Group;
import com.springfood.domain.model.User;
import com.springfood.domain.repository.GroupRepository;
import com.springfood.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtSecretUtils jwtSecretUtils;

    @Transactional
    public void create(User user, Boolean admin) {

        Optional<User> userExists = this.repository.findByEmail(user.getEmail());

        if (userExists.isPresent()) {
            throw new BadRequestException(String.format("O e-mail %s já existe", user.getEmail()));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User currentUser = this.repository.save(user);

        if (admin) {
            Group groupAdmin = this.groupRepository.findByName("ADMIN").orElseThrow(() -> new NotFoundException("Group de nome ADMIN não foi encontrado"));
            currentUser.getGroups().add(groupAdmin);
        }
    }

    @Transactional
    public User update(Long id, User user) {

        boolean userAuthenticated = this.jwtSecretUtils.isUserAuthenticated(id);

        if (!userAuthenticated) {
            throw new AccessDeniedException("Você não pode alterar os dados de outro usuário");
        }

        return this.repository.save(user);
    }

    public List<User> listAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Usuário de Id %d não foi encontrado", id)));
    }

    public void delete(Long id) {

        this.findById(id);

        this.repository.deleteById(id);
    }

    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {
        User user = this.findById(id);

        boolean userAuthenticated = this.jwtSecretUtils.isUserAuthenticated(id);

        if (!userAuthenticated) {
            throw new AccessDeniedException("Você não pode alterar os dados de outro usuário");
        }

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new BadRequestException("A senha informado, não confere com a senha atual");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
    }

}
