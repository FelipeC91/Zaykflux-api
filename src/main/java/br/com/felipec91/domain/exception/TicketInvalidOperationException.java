package br.com.felipec91.domain.exception;

import br.com.felipec91.application.exception_handler.ApiErrorException;

public class TicketInvalidOperationException extends ApiErrorException {
    public TicketInvalidOperationException(String message) {
        super(message);
    }
}
