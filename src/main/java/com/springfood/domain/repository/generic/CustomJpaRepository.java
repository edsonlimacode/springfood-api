package com.springfood.domain.repository.generic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/*
 * CustomJpaRepository - é uma interface generica costumizada, ela herda a jpaEepository e adiciona o metodo
 * findFirst() que será um metodo usado por qualquer repositorio, este tipo de costumização e quando você não
 * tem um metodo no JpaRepository do spring e quer adicionar-lo para que herdar poder usar ou seja vai servir para
 * qualquer repositorio que queira usar o metodo geral.
 *
 * Resumindo ele adiciona metodos ao repositorio padrao.
 * */

@NoRepositoryBean //diz ao spring para não gerenciar, é usada apenas como base.
public interface CustomJpaRepository<T, ID> extends JpaRepository<T, ID> {

    Optional<T> findFirst(); //quem implementar a inerface, tem acesso.

}
