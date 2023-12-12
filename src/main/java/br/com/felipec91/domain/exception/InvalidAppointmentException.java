package br.com.felipec91.domain.exception;

import br.com.felipec91.application.exception_handler.ApiErrorException;

public class InvalidAppointmentException extends ApiErrorException {

    public InvalidAppointmentException(String message) {
        super(message);
    }

    public InvalidAppointmentException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAppointmentException(Throwable cause) {
        super(cause);
    }

    public InvalidAppointmentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
