package com.saudemental.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata exceções de validação (@Valid) e retorna HTTP 400 (Bad Request).
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Trata exceções de Entidade Não Encontrada (customizadas) e retorna HTTP 404 (Not Found).
     */
    @ExceptionHandler({PacienteNaoEncontradoException.class, RegistroDiarioNaoEncontradoException.class})
    public ResponseEntity<String> handleNotFoundExceptions(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Trata exceções genéricas e retorna HTTP 500 (Internal Server Error).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        // Logar a exceção para debug
        ex.printStackTrace();
        return new ResponseEntity<>("Ocorreu um erro interno no servidor: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
