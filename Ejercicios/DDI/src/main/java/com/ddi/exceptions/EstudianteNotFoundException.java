package com.ddi.exceptions;

public class EstudianteNotFoundException extends RuntimeException {
    public EstudianteNotFoundException(String message) {
        super(message);
    }
}
