package com.analisecredito.exceptions;

import org.springframework.http.HttpStatus;

public abstract class AnaliseCreditoAbstractRuntimeException extends RuntimeException {

    public AnaliseCreditoAbstractRuntimeException() {
        super();
    }

    public AnaliseCreditoAbstractRuntimeException(String message) {
        super(message);
    }

    public AnaliseCreditoAbstractRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract HttpStatus getStatus();
}
