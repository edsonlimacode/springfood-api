package com.springfood.domain.interfaces;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import java.util.Map;
import java.util.Set;

public interface SendEmailService {

    void send(Message message);

    @Builder
    @Getter
    @Setter
    class Message {

        @Singular
        private Set<String> destinations;
        private String subject;
        private String body;

        @Singular
        private Map<String, Object> variables;
    }

}
