package com.springfood.api.openapi.model;

import com.springfood.domain.model.Kitchen;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Schema(name = "PageModelKitchen")
@Getter
public class PageModelKitchen extends PageModelOpenApi {

    @Schema(example = "[{\"id\":1,\"name\":\"Brasileira\"},{\"id\":2,\"name\":\"Tailandesa\"}]")
    private List<Object> data;
}
