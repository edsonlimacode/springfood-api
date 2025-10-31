package com.springfood.api.openapi.model;

import com.springfood.domain.model.Kitchen;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Schema(name = "PageModelOpenApi")
@Getter
public class PageModelOpenApi {

    @Schema(example = "[{\"id\":1,\"name\":\"Brasileira\"},{\"id\":2,\"name\":\"Tailandesa\"}]")
    private List<Kitchen> data;
    @Schema(example = "5")
    private Long totalElements;
    @Schema(example = "0")
    private Integer currentPage;
    @Schema(example = "2")
    private Integer size;
}
