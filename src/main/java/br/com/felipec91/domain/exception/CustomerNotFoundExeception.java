package br.com.felipec91.domain.exception;

import br.com.felipec91.application.exception_handler.ApiErrorException;

public class CustomerNotFoundExeception extends ApiErrorException {


    public CustomerNotFoundExeception(String message) {
        super(message);
    }

    public CustomerNotFoundExeception(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerNotFoundExeception(Throwable cause) {
        super(cause);
    }

    public CustomerNotFoundExeception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
