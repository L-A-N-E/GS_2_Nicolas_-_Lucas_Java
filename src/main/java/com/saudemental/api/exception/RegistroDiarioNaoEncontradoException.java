package com.saudemental.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RegistroDiarioNaoEncontradoException extends RuntimeException {
    public RegistroDiarioNaoEncontradoException(String message) {
        super(message);
    }
}
