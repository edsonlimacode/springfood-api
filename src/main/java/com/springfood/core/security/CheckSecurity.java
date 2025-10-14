package com.springfood.core.security;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface CheckSecurity {

    @PreAuthorize("hasAuthority('MASTER')")
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Master { }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Admin { }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MASTER')")
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface AdminAndMaster { }


    /*
     * returnObject → é o ResponseEntity
     * returnObject.body → é o seu OrderResponseDto
     * returnObject.body.user.id → finalmente acessa o id do usuário
     *
     * Permite que usuario comum que fez o pedido, que nao tem nenhum grupo possa ver seu pedido e qualquer um que tenha a permissao consultar
     * @PostAuthorize("hasAuthority('CONSULTAR_PEDIDO') or @jwtSecretUtils.getUserId() == returnObject.body.user.id")
     * */
    public @interface Orders {

        @PreAuthorize("isAuthenticated()")
        @PostAuthorize("hasAuthority('GERENCIAR_PEDIDO') or @jwtSecretUtils.isUserAuthenticated(returnObject.body.user.id)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface canSearch {
        }

    }

    public @interface Restaurants {

        @PreAuthorize("hasAuthority('GERENCIAR_RESTAURANTES') or @jwtSecretUtils.managerRestaurants(#id)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface Manager {
        }

        @PreAuthorize("isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface ReadOnly {
        }

    }
}
