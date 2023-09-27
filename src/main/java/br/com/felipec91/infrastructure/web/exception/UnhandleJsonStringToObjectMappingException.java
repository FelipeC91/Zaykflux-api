package br.com.felipec91.infrastructure.web.exception;

import br.com.felipec91.application.exception_handler.ApiErrorException;

public class UnhandleJsonStringToObjectMappingException extends ApiErrorException {

    public UnhandleJsonStringToObjectMappingException(String message) {
        super(message);
    }

    public UnhandleJsonStringToObjectMappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnhandleJsonStringToObjectMappingException(Throwable cause) {
        super(cause);
    }

    public UnhandleJsonStringToObjectMappingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
