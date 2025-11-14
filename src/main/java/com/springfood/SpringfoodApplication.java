package com.springfood;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.springfood.core.security.Base64ProtocolResolver;
import com.springfood.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
//diz ao spring que será uma classe base tbm.
public class SpringfoodApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        // SpringApplication.run(SpringfoodApplication.class, args);
        var app = new SpringApplication(SpringfoodApplication.class);
        app.addListeners(new Base64ProtocolResolver());
        app.run(args);
    }

}
