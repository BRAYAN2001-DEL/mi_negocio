package com.minegocio.clientes.exception;

public class ClienteYaExisteException extends RuntimeException {
    public ClienteYaExisteException(String mensaje) {
        super(mensaje);
    }
}