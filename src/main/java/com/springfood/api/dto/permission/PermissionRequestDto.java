package com.springfood.api.dto.permission;


import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class PermissionRequestDto {

    @NotBlank
    private String name;
    private String description;
}
