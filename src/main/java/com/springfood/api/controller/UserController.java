package com.springfood.api.controller;


import com.springfood.api.dto.user.UserCreateRequestDto;
import com.springfood.api.dto.user.UserResponseDto;
import com.springfood.api.dto.user.UserUpdatePasswordRequestDto;
import com.springfood.api.dto.user.UserUpdateRequestDto;
import com.springfood.api.mapper.UserMapper;
import com.springfood.api.openapi.controller.UserControllerDoc;
import com.springfood.core.security.CheckSecurity;
import com.springfood.domain.model.User;
import com.springfood.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController implements UserControllerDoc {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@Valid @RequestBody UserCreateRequestDto userCreateRequestDto) {

        User user = this.mapper.toModel(userCreateRequestDto);

        this.userService.create(user, userCreateRequestDto.getAdmin());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id, @Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto) {

        User user = this.userService.findById(id);

        this.mapper.copyUserProperties(userUpdateRequestDto, user);

        User userUpdated = this.userService.update(id, user);

        UserResponseDto userResponseDto = this.mapper.toDto(userUpdated);

        return ResponseEntity.ok(userResponseDto);
    }

    @PutMapping("/{id}/password")
    public void updatePassword(@PathVariable Long id, @RequestBody UserUpdatePasswordRequestDto passwordRequestDto) {
        this.userService.updatePassword(id, passwordRequestDto.getPassword(), passwordRequestDto.getNewPassword());
    }

    @CheckSecurity.AdminAndMaster
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> list() {
        List<User> users = this.userService.listAll();

        List<UserResponseDto> userResponseDtoList = this.mapper.toListDto(users);

        return ResponseEntity.ok(userResponseDtoList);
    }

    @CheckSecurity.AdminAndMaster
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        User user = this.userService.findById(id);

        UserResponseDto userResponseDto = this.mapper.toDto(user);

        return ResponseEntity.ok(userResponseDto);
    }

    @CheckSecurity.AdminAndMaster
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        this.userService.delete(id);
    }

}
