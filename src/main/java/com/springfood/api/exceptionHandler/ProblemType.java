package com.springfood.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    TYPE_INTERNAL_SERVER_ERRO("/erro-da-aplicação", "Erro interno"),
    TYPE_NOT_FOUND("/nao-encontrado", "Não encontrado"),
    TYPE_BAD_REQUEST("/requisicao-invalida", "Informações inválidas"),
    TYPE_CONFLICT("/conflito", "Em conflito"),
    TYPE_FORBIDEN("/acesso-negado", "Acesso negado");

    private String path;
    private String title;

    ProblemType(String path, String title) {
        this.path = "https//localhost:8080" + path;
        this.title = title;
    }
}
