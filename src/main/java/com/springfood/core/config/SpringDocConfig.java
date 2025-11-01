package com.springfood.core.config;


import com.springfood.api.exceptionHandler.ProblemDetails;
import com.springfood.api.openapi.model.PageModelKitchen;
import com.springfood.api.openapi.model.PageModelOpenApi;
import com.springfood.api.openapi.model.PageModelOrder;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@SecurityScheme(name = "security_auth",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(authorizationCode = @OAuthFlow(
                authorizationUrl = "${springdoc.oAuthFlow.authorizationUrl}",
                tokenUrl = "${springdoc.oAuthFlow.tokenUrl}"
                /*scopes = {
                        @OAuthScope(name = "READ", description = "read scope"),
                        @OAuthScope(name = "WRITE", description = "write scope")
                }*/
        )))
public class SpringDocConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("SpringShop API")
                        .description("Spring shop sample application")
                        .version("v0.0.1"))
                .tags(List.of(
                        new Tag().name("Grupos").description("Gerencia os grupos"),
                        new Tag().name("Restaurantes").description("Gerencia os restaurantes"),
                        new Tag().name("Pedidos").description("Gerencia os pedidos"),
                        new Tag().name("Usuários").description("Gerencia os usuários")
                ))
                .components(new Components().schemas(this.schemasMap()));
    }

    @Bean
    public OpenApiCustomizer openApiCustomiser() {

        return openApi -> {

            openApi.getPaths()
                    .values()
                    .forEach(pathItem -> pathItem.readOperationsMap()
                            .forEach(((httpMethod, operation) -> {
                                        ApiResponses responses = operation.getResponses();

                                        switch (httpMethod) {
                                            case GET:
                                                responses.addApiResponse("500", new ApiResponse().description("Internal server error"));
                                                responses.addApiResponse("405", new ApiResponse().description("Método não permitido"));
                                                responses.addApiResponse("406", new ApiResponse().description("O servidor não consegue gerar uma resposta no formato solicitado pelo cliente no cabeçalho Accept"));
                                                break;
                                            case POST:
                                                responses.addApiResponse("400", new ApiResponse().description("Dados informados incorreto"));
                                                responses.addApiResponse("500", new ApiResponse().description("Internal server error"));
                                                responses.addApiResponse("405", new ApiResponse().description("Método não permitido"));
                                                responses.addApiResponse("406", new ApiResponse().description("O servidor não consegue gerar uma resposta no formato solicitado pelo cliente no cabeçalho Accept"));
                                                break;
                                            case PUT:
                                                responses.addApiResponse("400", new ApiResponse().description("ID recurso inválido ou dados da requisição inválida"));
                                                responses.addApiResponse("404", new ApiResponse().description("Recurso não encontrado"));
                                                responses.addApiResponse("500", new ApiResponse().description("Internal server error"));
                                                responses.addApiResponse("405", new ApiResponse().description("Método não permitido"));
                                                responses.addApiResponse("406", new ApiResponse().description("O servidor não consegue gerar uma resposta no formato solicitado pelo cliente no cabeçalho Accept"));
                                                break;
                                            case DELETE:
                                                responses.addApiResponse("400", new ApiResponse().description("ID recurso inválido"));
                                                responses.addApiResponse("404", new ApiResponse().description("Recurso não encontrado"));
                                                responses.addApiResponse("500", new ApiResponse().description("Internal server error"));
                                                responses.addApiResponse("405", new ApiResponse().description("Método não permitido"));
                                                responses.addApiResponse("406", new ApiResponse().description("O servidor não consegue gerar uma resposta no formato solicitado pelo cliente no cabeçalho Accept"));
                                                break;
                                        }
                                    })
                            )
                    );
        };
    }

    private Map<String, Schema> schemasMap() {
        final Map<String, Schema> schemaMap = new HashMap<>();

        var problemDetailsSchema = ModelConverters.getInstance().read(ProblemDetails.class);
        var pageModelKitchenOpenApi = ModelConverters.getInstance().read(PageModelKitchen.class);
        var pageModelOrderOpenApi = ModelConverters.getInstance().read(PageModelOrder.class);
        var problemDetailsSchemaField = ModelConverters.getInstance().read(ProblemDetails.Field.class);

        schemaMap.putAll(problemDetailsSchema);
        schemaMap.putAll(problemDetailsSchemaField);
        schemaMap.putAll(pageModelKitchenOpenApi);
        schemaMap.putAll(pageModelOrderOpenApi);

        return schemaMap;
    }
}


















