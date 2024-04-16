package com.voll.med.api.infra.exception;

public class ValidacaoException extends RuntimeException {
    public ValidacaoException(String mensage) {
        super(mensage);
    }
}
