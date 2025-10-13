package com.springfood.api.dto.address;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponseDto {

    private String cep;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
}
