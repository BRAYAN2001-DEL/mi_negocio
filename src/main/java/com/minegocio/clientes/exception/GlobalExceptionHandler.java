package com.minegocio.clientes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClienteYaExisteException.class)
    public ResponseEntity<Map<String, Object>> manejarClienteYaExiste(ClienteYaExisteException ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("timestamp", LocalDateTime.now());
        respuesta.put("mensaje", ex.getMessage());
        respuesta.put("codigo", HttpStatus.CONFLICT.value());

        return new ResponseEntity<>(respuesta, HttpStatus.CONFLICT); // 409
    }

}
