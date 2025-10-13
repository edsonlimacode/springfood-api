package com.springfood.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

   /* @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*");
    }*/

    /*
     * adiciona no cabeçalho o ETAG um hash da resposta em cache caso o cache-control esteja ativado, apartir das proximas requisicoes se o max-age tiver expirado
     * ele adiciona o if-none-macth e verifica se o que esta em cache mudou, se nada mudou retorna o mesmo dado, caso mudou ele
     * gera outra requisição salva no cache e gera um novo ETAG
     *
     * o if-none-macth so é setado no cabeçalho caso o tempo de cache max-age tenha expirado.
     *
     * Resumindo - o Etag evita novas requisicoes caso o conteudo nao tenha mudado e retorna o que esta no cachce.
     *  */
    @Bean

    public Filter shallowEtag() {
        return new ShallowEtagHeaderFilter();
    }
}
