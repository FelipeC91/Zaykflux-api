package br.com.felipec91.domain.exception;

import br.com.felipec91.application.exception_handler.ApiErrorException;

public class ContactAlreadyLinkendWithCliente extends ApiErrorException {

    public ContactAlreadyLinkendWithCliente(String message) {
        super(message);
    }

    public ContactAlreadyLinkendWithCliente(String message, Throwable cause) {
        super(message, cause);
    }

    public ContactAlreadyLinkendWithCliente(Throwable cause) {
        super(cause);
    }

    public ContactAlreadyLinkendWithCliente(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
