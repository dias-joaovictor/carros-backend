package br.com.dias.cars.exception;

import jakarta.persistence.EntityNotFoundException;

public class RecursoNaoEncontradoException extends EntityNotFoundException {
    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
