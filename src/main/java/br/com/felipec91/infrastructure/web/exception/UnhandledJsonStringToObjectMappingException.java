package br.com.felipec91.infrastructure.web.exception;

import br.com.felipec91.application.exception_handler.ApiErrorException;

public class UnhandledJsonStringToObjectMappingException extends ApiErrorException {

    public UnhandledJsonStringToObjectMappingException(String message) {
        super(message);
    }

    public UnhandledJsonStringToObjectMappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnhandledJsonStringToObjectMappingException(Throwable cause) {
        super(cause);
    }

    public UnhandledJsonStringToObjectMappingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
