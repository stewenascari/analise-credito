package com.analisecredito.exceptions;

import org.springframework.http.HttpStatus;

public class RecursoNaoEncontradoException extends AnaliseCreditoAbstractRuntimeException {

    public RecursoNaoEncontradoException(String notFoundMessage) {
        super("Recurso não encontrado. " + notFoundMessage);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
