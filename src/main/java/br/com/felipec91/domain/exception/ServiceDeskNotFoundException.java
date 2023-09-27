package br.com.felipec91.domain.exception;

import br.com.felipec91.application.exception_handler.ApiErrorException;

public class ServiceDeskNotFoundException extends ApiErrorException {

    public ServiceDeskNotFoundException(String message) {
        super(message);
    }

    public ServiceDeskNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceDeskNotFoundException(Throwable cause) {
        super(cause);
    }

    public ServiceDeskNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
