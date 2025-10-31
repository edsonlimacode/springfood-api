package com.springfood.api.controller;


import com.springfood.api.dto.kitchen.KitchenRequestDto;
import com.springfood.api.dto.kitchen.KitchenResponseDto;
import com.springfood.api.mapper.KitchenMapper;
import com.springfood.api.openapi.controller.KitchenControllerDoc;
import com.springfood.core.security.CheckSecurity;
import com.springfood.domain.model.Kitchen;
import com.springfood.domain.service.KitchenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kitchens")
public class KitchenController implements KitchenControllerDoc {

    @Autowired
    private KitchenService kitchenService;

    @Autowired
    private KitchenMapper mapper;

    @CheckSecurity.Master
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody KitchenRequestDto kitchenRequestDto) {

        Kitchen kitchen = this.mapper.toModel(kitchenRequestDto);

        this.kitchenService.create(kitchen);
    }

    @CheckSecurity.Master
    @PutMapping("/{id}")
    public ResponseEntity<KitchenResponseDto> update(@PathVariable Long id, @Valid
    @RequestBody KitchenRequestDto kitchenRequestDto) {

        Kitchen kitchen = this.mapper.toModel(kitchenRequestDto);
        Kitchen kitchenUpdated = this.kitchenService.update(id, kitchen);

        KitchenResponseDto kitchenResponseDto = this.mapper.toDto(kitchenUpdated);

        return ResponseEntity.ok(kitchenResponseDto);
    }

    @CheckSecurity.AdminAndMaster
    @GetMapping
    public Page<KitchenResponseDto> list(Pageable pageable) {
        Page<Kitchen> kitchens = this.kitchenService.listAll(pageable);

        List<KitchenResponseDto> kitchenResponseDtoList = this.mapper.toListDto(kitchens.getContent());

        return new PageImpl<>(kitchenResponseDtoList, pageable, kitchens.getTotalElements());
    }

    @CheckSecurity.AdminAndMaster
    @GetMapping("/{id}")
    public ResponseEntity<KitchenResponseDto> findOne(@PathVariable Long id) {
        Kitchen kitchen = this.kitchenService.findById(id);

        KitchenResponseDto kitchenResponseDto = this.mapper.toDto(kitchen);

        return ResponseEntity.ok(kitchenResponseDto);
    }

    @CheckSecurity.Master
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        this.kitchenService.delete(id);
    }
}
