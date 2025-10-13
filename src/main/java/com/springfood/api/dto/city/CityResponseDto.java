package com.springfood.api.dto.city;

import com.springfood.domain.model.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityResponseDto {

    private Long id;
    private String name;
    private State state;

}
