package br.com.felipec91.domain.exception;

import br.com.felipec91.application.exception_handler.ApiErrorException;

public class CustomerAlreadyExistsException extends ApiErrorException {

public CustomerAlreadyExistsException(String message) {
        super(message);
        }

public CustomerAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
        }

public CustomerAlreadyExistsException(Throwable cause) {
        super(cause);
        }

public CustomerAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        }
        }

