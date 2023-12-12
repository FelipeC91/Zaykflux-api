package br.com.felipec91.domain.exception;

import br.com.felipec91.application.exception_handler.ApiErrorException;

public class AccontableAlreadyHaveAppointmentInThisPeriodException extends ApiErrorException {

    public AccontableAlreadyHaveAppointmentInThisPeriodException(String message) {
        super(message);
    }

    public AccontableAlreadyHaveAppointmentInThisPeriodException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccontableAlreadyHaveAppointmentInThisPeriodException(Throwable cause) {
        super(cause);
    }

    public AccontableAlreadyHaveAppointmentInThisPeriodException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
