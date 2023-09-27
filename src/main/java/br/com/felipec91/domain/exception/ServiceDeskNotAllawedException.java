package br.com.felipec91.domain.exception;

import br.com.felipec91.application.exception_handler.ApiErrorException;

public class ServiceDeskNotAllawedException extends ApiErrorException {

    public ServiceDeskNotAllawedException(String message) {
        super(message);
    }

    public ServiceDeskNotAllawedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceDeskNotAllawedException(Throwable cause) {
        super(cause);
    }

    public ServiceDeskNotAllawedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
